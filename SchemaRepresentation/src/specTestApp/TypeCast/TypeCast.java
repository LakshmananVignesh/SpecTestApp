//$Id$
package specTestApp.TypeCast;

import java.util.Map;

import org.json.JSONObject;

public interface TypeCast<T> {
	public Map<String,Object> typeCastToSchemaRepresentation(T customRepresentation,JSONObject mappings,JSONObject schema);
	public T typeCastToCustomRepresentation(Map<String,Object> schemaRepresenation,JSONObject mappings,JSONObject schema);
}
