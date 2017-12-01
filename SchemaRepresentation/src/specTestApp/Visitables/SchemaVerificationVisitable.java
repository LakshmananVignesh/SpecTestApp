//$Id$
package specTestApp.Visitables;

import java.util.Map;

import specTestApp.Vistors.SchemaVerificationVisitor;

public interface SchemaVerificationVisitable {
	public boolean accept(SchemaVerificationVisitor v);
	public Map<String,Object> toSchemaRepresentation();
}
