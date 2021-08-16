package genelectrovise.bizarre.spring.api.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.ServiceType;

public class DeserializeRegisterServiceRequest extends JsonDeserializer<RegisterServiceRequest> {

	@Override
	public RegisterServiceRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		JsonNode node = p.getCodec().readTree(p);

		String host = node.get("host").toString();
		int port = ((IntNode) node.get("port")).numberValue().intValue();
		ServiceType type = () -> node.get("service_type").toString();

		return new RegisterServiceRequest() {
			
			@Override
			public ServiceType getServiceType() {
				return type;
			}
			
			@Override
			public int getServicePort() {
				return port;
			}
			
			@Override
			public String getServiceHost() {
				return host;
			}
		};
	}

}
