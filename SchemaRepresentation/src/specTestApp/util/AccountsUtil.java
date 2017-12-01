//$Id$
package specTestApp.util;

import org.json.JSONObject;

public class AccountsUtil {
	public static JSONObject getSampleJsonObject(){
		JSONObject sampleJSON = new JSONObject();
		sampleJSON.put("id","10000006");
		sampleJSON.put("accountName", "Zylker");
		sampleJSON.put("industry", "Business");
		sampleJSON.put("phone", "9159543762");
		sampleJSON.put("website", "www.zylker.com");
		sampleJSON.put("email", "zylker@zoho.com");
		sampleJSON.put("ownerId", "12000002");
		sampleJSON.put("createdTime", "1512038841790");
		sampleJSON.put("modifiedTime","1512038855698");
		sampleJSON.put("createdBy", "12000001");
		return sampleJSON;
	}
}
