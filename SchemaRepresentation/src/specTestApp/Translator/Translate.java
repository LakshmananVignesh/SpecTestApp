//$Id$
package specTestApp.Translator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import specTestApp.schema.AccountSchema;

public class Translate {
	private Map<String,Object> schemaRepresentation = new LinkedHashMap<String,Object>();
	
	public Translate(JSONObject schemaJson){
		Iterator<String> namedValues = schemaJson.keys();
		while(namedValues.hasNext()){
			schemaRepresentation.put(namedValues.next(), null);
		}
	}
	
	public Map<String,Object> getRepresentation(){
		return this.schemaRepresentation;
	}
}
 