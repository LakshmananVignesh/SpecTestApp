//$Id$
package specTestApp.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import specTestApp.VistablesImpl.JSONObjectSchemaVisistablesImpl;


public class SchemaRepresentationToJSONObject implements SchemaRepresentationToCustomRepresentation<JSONObject>{

	@Override
	public JSONObject getCustomRepresentation(Map<String, Object> schemaObject) {
		Map<String,String> representationMapping = representationToSchemaMapping();
		Iterator<String> keys = schemaObject.keySet().iterator();
		JSONObject object = new JSONObject();
		while(keys.hasNext()){
			String key = keys.next();
			object.put(representationMapping.get(key), schemaObject.get(key));
		}
		return object;
	}
	
	private Map<String,String> representationToSchemaMapping(){
		Map<String,String> dbRepresentation = new HashMap<String,String>(); 
		JSONArray representation = JSONObjectSchemaVisistablesImpl.getJsonRepresentationMapping().getJSONArray("mappings");
		for(int i=0;i<representation.length();i++){
			JSONObject repMapping = representation.getJSONObject(i);
			dbRepresentation.put(repMapping.getString("modelVariable"), repMapping.getString("name"));
		}
		return dbRepresentation;
	}
}
