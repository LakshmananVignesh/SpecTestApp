//$Id$
package specTestApp.MySQLConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;


public class MySQLConnect {
	private static final String url = "jdbc:mysql://lakshmanan-zt71:3306/MockDatabase";
	private static final String username = "root";
	private static final String password = "";
	private static volatile MySQLConnect instance;
	static Connection con = null;
	private MySQLConnect(){
		
	}
	
	private static void setCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Connection getCon() throws Exception {
        if (con == null) {
            setCon();
        }
        return con;
    }
	
	public static MySQLConnect getInstance(){
		if(instance == null){
			synchronized(MySQLConnect.class){
				if(instance == null){
					instance = new MySQLConnect();
				}
			}
		}
		return instance;
	}
	
	public Map<String,Object> get(String query){
		Map<String,Object> tableValues = new LinkedHashMap<String,Object>();
		try{
			ResultSet rs = execute(query);
			ResultSetMetaData rd=rs.getMetaData();
			while(rs.next()){
				for( int i=1; i<=rd.getColumnCount();i++){
					tableValues.put(rd.getTableName(i).toUpperCase()+"."+rd.getColumnName(i), rs.getObject(i));
				}
			}
			return tableValues;
		}
		catch(Exception e){
			System.out.println("SQL Exception");
			return null;
		}
	}

	private ResultSet execute(String query)throws Exception {
		Statement stmt = getCon().createStatement();
		return stmt.executeQuery(query);
	}
	
	private int executeUpdate(String query)throws Exception{
		Statement stmt = getCon().createStatement();
		return stmt.executeUpdate(query);
	}
	
	public boolean insert(String query){
		try {
			return executeUpdate(query)>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
