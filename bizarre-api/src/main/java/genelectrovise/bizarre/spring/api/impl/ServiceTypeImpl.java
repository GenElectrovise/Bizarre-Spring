package genelectrovise.bizarre.spring.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import genelectrovise.bizarre.spring.api.inter.ServiceType;

public class ServiceTypeImpl implements ServiceType {

	String name;

	@JsonCreator
	public ServiceTypeImpl(@JsonProperty("name") String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
