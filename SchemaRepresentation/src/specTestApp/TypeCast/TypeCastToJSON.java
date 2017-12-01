//$Id$
package specTestApp.TypeCast;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import specTestApp.Translator.Translate;


public class TypeCastToJSON implements TypeCast<JSONObject>{

	@Override
	public Map<String, Object> typeCastToSchemaRepresentation(
			JSONObject customRepresentation, JSONObject mappings,
			JSONObject schema) {
		Translate translator = new Translate(schema);
		Map<String,Object> schemaRepresentation = new HashMap<String,Object>();
		return null;
	}

	@Override
	public JSONObject typeCastToCustomRepresentation(
			Map<String, Object> schemaRepresenation, JSONObject mappings,
			JSONObject schema) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
