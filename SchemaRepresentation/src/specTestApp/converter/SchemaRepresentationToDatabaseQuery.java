//$Id$
package specTestApp.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.JSONArray;
import org.json.JSONObject;

import specTestApp.VistablesImpl.DataBaseSchemaVisitablesImpl;

public class SchemaRepresentationToDatabaseQuery implements SchemaRepresentationToCustomRepresentation<String> {
	String templateString = "INSERT INTO CRMACCOUNT SET ACCOUNTID=${CRMACCOUNT.ACCOUNTID},ACCOUNTNAME='${CRMACCOUNT.ACCOUNTNAME}',INDUSTRY='${CRMACCOUNT.INDUSTRY}',WEBSITE='${CRMACCOUNT.WEBSITE}',OWNERID=${CRMACCOUNT.OWNERID},EMAIL='${CRMACCOUNT.EMAIL}',CREATEDTIME=${CRMACCOUNT.CREATEDTIME},MODIFIEDTIME=${CRMACCOUNT.MODIFIEDTIME},CREATEDBY=${CRMACCOUNT.CREATEDBY},PHONE=${CRMACCOUNT.PHONE}";
	
	@Override
	public String getCustomRepresentation(Map<String, Object> schemaObject) {
		Map<String,String> representationMapping = representationToSchemaMapping();
		Map<String,String> valueMap = convertSchemaObjectToValuMap(schemaObject,representationMapping);
		StrSubstitutor sub = new StrSubstitutor(valueMap);
		String resolvedString = sub.replace(templateString);
		return resolvedString;
	}
	private Map<String, String> convertSchemaObjectToValuMap(
			Map<String, Object> schemaObject,
			Map<String, String> representationMapping) {
		Map<String,String> valuesMap = new HashMap<String,String>();
		Iterator<String> schemaObj = schemaObject.keySet().iterator();
		while(schemaObj.hasNext()){
			String key = schemaObj.next();
			Object object = schemaObject.get(key);
			valuesMap.put(representationMapping.get(key), object.toString());
		}
		return valuesMap;
	}
	private Map<String,String> representationToSchemaMapping(){
		Map<String,String> dbRepresentation = new HashMap<String,String>(); 
		JSONArray representation = DataBaseSchemaVisitablesImpl.getJsonRepresentationMapping().getJSONArray("mappings");
		for(int i=0;i<representation.length();i++){
			JSONObject repMapping = representation.getJSONObject(i);
			dbRepresentation.put(repMapping.getString("modelVariable"), repMapping.getString("name"));
		}
		return dbRepresentation;
	}
	private static final String Id = "CRMACCOUNT.ACCOUNTID";
	private static final String accountName = "CRMACCOUNT.ACCOUNTNAME";
	private static final String industry = "CRMACCOUNT.INDUSTRY";
	private static final String phone = "CRMACCOUNT.PHONE";
	private static final String website = "CRMACCOUNT.WEBSITE";
	private static final String ownerId = "CRMACCOUNT.OWNERID";
	private static final String email = "CRMACCOUNT.EMAIL";
	private static final String createdTime = "CRMACCOUNT.CREATEDTIME";
	private static final String modifiedTime = "CRMACCOUNT.ACCOUNTID";
	private static final String createdBy = "CRMACCOUNT.CREATEDBY";
	
}
