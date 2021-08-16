package genelectrovise.bizarre.spring.api.inter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import genelectrovise.bizarre.spring.api.impl.RegisterServiceRequestImpl;

@JsonDeserialize(as = RegisterServiceRequestImpl.class)
public interface RegisterServiceRequest {

	ServiceType getServiceType();

	String getServiceHost();

	int getServicePort();
	
	@Override
	String toString();
}
