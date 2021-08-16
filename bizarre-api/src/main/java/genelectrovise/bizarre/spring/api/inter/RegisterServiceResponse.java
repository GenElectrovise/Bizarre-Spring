package genelectrovise.bizarre.spring.api.inter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import genelectrovise.bizarre.spring.api.impl.RegisterServiceResponseImpl;

@JsonDeserialize(as = RegisterServiceResponseImpl.class)
public interface RegisterServiceResponse {

	int ok();
}
