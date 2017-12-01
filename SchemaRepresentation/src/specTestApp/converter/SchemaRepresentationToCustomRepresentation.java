//$Id$
package specTestApp.converter;

import java.util.Map;

public interface SchemaRepresentationToCustomRepresentation<T> {
	T getCustomRepresentation(Map<String,Object> schemaObject);
}
