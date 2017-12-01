//$Id$
package specTestApp.schema;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Singleton
 * @author lakshmanan-zt71
 *
 */
public class AccountSchema {
	
	private static volatile AccountSchema instance;
	
	private static JSONObject schemaRepresentation = new JSONObject();
	
	private static JSONObject accountSchema = new JSONObject();
	
	private static Map<String,Object> accountObject = new HashMap<String,Object>();
	
	private AccountSchema(){
		
		try {
			String fileName = "/Users/lakshmanan-zt71/Downloads/eclipse/Eclipse.app/Contents/MacOS/ZIDE/SchemaRepresentation/accounts.json";
			final JSONTokener tokener = new JSONTokener(new String(Files.readAllBytes(Paths.get(fileName))));
			accountSchema = new JSONObject(tokener);
			schemaRepresentation = getSchema();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
		
	public static AccountSchema getInstance(){
		if(instance == null){
			synchronized(AccountSchema.class){
				if(instance == null){
					instance = new AccountSchema();
				}
			}
		}
		return instance;
	}
	
	private JSONObject getSchema(){
		JSONObject schema = new JSONObject();
		JSONArray array = accountSchema.getJSONArray("namedValues");
		for(int i=0;i<array.length();i++){
			JSONObject keyObj =array.getJSONObject(i);
			schema.put(keyObj.getString("name"), keyObj.getJSONObject("type"));
			accountObject.put(keyObj.getString("name"), null);
		}
		return schema;
	}
	
	public JSONObject getSchemaRepresnetation(){
		return schemaRepresentation;
	}
	public Map<String,Object> getAccountMap(){
		return accountObject;
	}
}
