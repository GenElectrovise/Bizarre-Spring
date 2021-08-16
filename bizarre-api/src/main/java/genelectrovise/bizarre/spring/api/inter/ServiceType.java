package genelectrovise.bizarre.spring.api.inter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import genelectrovise.bizarre.spring.api.impl.ServiceTypeImpl;

@JsonDeserialize(as = ServiceTypeImpl.class)
public interface ServiceType {

	String getName();

}
