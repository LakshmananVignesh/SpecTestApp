//$Id$
package specTestApp.converter;

import java.util.Map;

public class SchemaRepresentationToString implements SchemaRepresentationToCustomRepresentation<String>{

	@Override
	public String getCustomRepresentation(Map<String, Object> schemaObject) {
		String schemaString = "accountName: "+schemaObject.get("accountName").toString()+"accountId: "+schemaObject.get("accountId");
		return schemaString;
	}

}
