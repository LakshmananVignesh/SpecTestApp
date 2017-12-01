//$Id$
package specTestApp.implementation;

import java.util.Map;

import org.json.JSONObject;

import specTestApp.Visitables.SchemaVerificationVisitable;
import specTestApp.VistablesImpl.JSONObjectSchemaVisistablesImpl;
import specTestApp.Vistors.SchemaVerificationVisitor;
import specTestApp.VistorsImpl.SchemaVerificationVisitorImpl;
import specTestApp.util.AccountsUtil;

public class Implementation {

	public static void main(String[] args){
		JSONObject accountObject = AccountsUtil.getSampleJsonObject();
		
		//Visitor
		final SchemaVerificationVisitor visitor = new SchemaVerificationVisitorImpl();
		
		//Visitable
		SchemaVerificationVisitable jsonVisitable = new JSONObjectSchemaVisistablesImpl(accountObject);
		
		if(jsonVisitable.accept(visitor)){
			System.out.println("accepted");
		}
	}
	
} 
