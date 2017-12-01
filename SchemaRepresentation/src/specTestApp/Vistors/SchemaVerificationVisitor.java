package specTestApp.Vistors;
import java.util.Map;

import org.json.JSONObject;

//$Id$

public interface SchemaVerificationVisitor {
 public boolean visit(Object element,JSONObject typeObject);
}
