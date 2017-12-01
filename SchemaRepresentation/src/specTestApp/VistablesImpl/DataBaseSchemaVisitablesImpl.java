//$Id$
package specTestApp.VistablesImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import specTestApp.Translator.Translate;
import specTestApp.Visitables.SchemaVerificationVisitable;
import specTestApp.Vistors.SchemaVerificationVisitor;
import specTestApp.schema.AccountSchema;

public class DataBaseSchemaVisitablesImpl implements SchemaVerificationVisitable{

	private Map<String,Object> accountsData = new HashMap<String,Object>();
	private JSONObject schema = AccountSchema.getInstance().getSchemaRepresnetation();
	private JSONObject jsonRepresentationObject = getJsonRepresentationMapping();
	
	public DataBaseSchemaVisitablesImpl(Map<String,Object> accountsData){
		this.accountsData = accountsData;
	}
	
	@Override
	public boolean accept(SchemaVerificationVisitor v) {
		Iterator<String> iter = accountsData.keySet().iterator();
		Map<String,String> schemaMapping = getMappingforSchema();
		while(iter.hasNext()){
			String key = iter.next();
			if(!v.visit(accountsData.get(key),schema.getJSONObject(schemaMapping.get(key)))){
					return false;
			}
		}
		return true;
	}

	@Override
	public Map<String, Object> toSchemaRepresentation() {
		Translate translator = new Translate(schema);
		Map<String,Object> translatedMap = translator.getRepresentation();
		Iterator<String> iter = accountsData.keySet().iterator();
		Map<String,String> schemaMapping = getMappingforSchema();
		while(iter.hasNext()){
			String key = iter.next();
			translatedMap.put(schemaMapping.get(key), accountsData.get(key));
		}
		return translatedMap;
	}
	
	public static JSONObject getJsonRepresentationMapping() {
		try{
			String fileName = "/Users/lakshmanan-zt71/Downloads/eclipse/Eclipse.app/Contents/MacOS/ZIDE/SchemaRepresentation/accountsDBRepresentation.json";
			final JSONTokener tokener = new JSONTokener(new String(Files.readAllBytes(Paths.get(fileName))));
			return new JSONObject(tokener);
		}
		catch(Exception e){
			return null;
		}
	}
	private Map<String,String> getMappingforSchema(){
		Map<String,String> mappings = new LinkedHashMap<String,String>();
		if(jsonRepresentationObject != null){
			JSONArray mappingsJson = jsonRepresentationObject.getJSONArray("mappings");
			for(int iter=0;iter<mappingsJson.length();iter++){
				JSONObject mappingObject = mappingsJson.getJSONObject(iter);
				mappings.put(mappingObject.getString("name"),mappingObject.getString("modelVariable"));
			}
		}
		return mappings;
	}
}
