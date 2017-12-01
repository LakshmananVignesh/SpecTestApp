//$Id$
package specTestApp.implementation;

import java.util.Map;

import org.json.JSONObject;

import specTestApp.MySQLConnector.MySQLConnect;
import specTestApp.Visitables.SchemaVerificationVisitable;
import specTestApp.VistablesImpl.DataBaseSchemaVisitablesImpl;
import specTestApp.VistablesImpl.JSONObjectSchemaVisistablesImpl;
import specTestApp.Vistors.SchemaVerificationVisitor;
import specTestApp.VistorsImpl.SchemaVerificationVisitorImpl;
import specTestApp.converter.SchemaRepresentationToCustomRepresentation;
import specTestApp.converter.SchemaRepresentationToDatabaseQuery;
import specTestApp.converter.SchemaRepresentationToJSONObject;
import specTestApp.util.AccountsUtil;

public class ImplementationDatabase {

	public static void main(String[] args){
		JSONObject accountObject = AccountsUtil.getSampleJsonObject();
		
		//Visitor
		final SchemaVerificationVisitor visitor = new SchemaVerificationVisitorImpl();
		
		//Visitable
		SchemaVerificationVisitable jsonVisitable = new JSONObjectSchemaVisistablesImpl(accountObject);
		
		if(jsonVisitable.accept(visitor)){
			System.out.println("accepted");
			SchemaRepresentationToCustomRepresentation<String> queryRepresentation = new SchemaRepresentationToDatabaseQuery();
			String query = queryRepresentation.getCustomRepresentation(jsonVisitable.toSchemaRepresentation());
			//System.out.println(query);
			MySQLConnect.getInstance().insert(query);
		}
		String query = "Select * from CRMACCOUNT where accountId='10000006'";
		Map<String,Object> resultSet = MySQLConnect.getInstance().get(query);
		
		//Visitor
				
		//Visitable
		SchemaVerificationVisitable dbVisitable = new DataBaseSchemaVisitablesImpl(resultSet);
		
		if(dbVisitable.accept(visitor)){
			System.out.println("accept");
			SchemaRepresentationToCustomRepresentation<JSONObject> jsonRepresentation = new SchemaRepresentationToJSONObject();
			System.out.println(jsonRepresentation.getCustomRepresentation(dbVisitable.toSchemaRepresentation()));
		}
	}
	
} 
