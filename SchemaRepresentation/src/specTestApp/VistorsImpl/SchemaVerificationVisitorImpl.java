//$Id$
package specTestApp.VistorsImpl;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import specTestApp.Vistors.SchemaVerificationVisitor;

public class SchemaVerificationVisitorImpl implements SchemaVerificationVisitor{

	@Override
	public boolean visit(Object element, JSONObject typeObject) {
		String type = typeObject.getString("name");
		switch(type){
		case "id":{
					return element instanceof Long;
				}
		case "basicString":{
					return element instanceof String;
				}
		default:
				return true;
		}
	}


	
}
