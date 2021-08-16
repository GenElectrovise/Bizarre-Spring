package genelectrovise.bizarre.spring.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

public class RegisterServiceResponseImpl implements RegisterServiceResponse {

	int ok;

	@JsonCreator
	public RegisterServiceResponseImpl(@JsonProperty("ok") int ok) {
		this.ok = ok;
	}

	@Override
	public int ok() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}
	
	@Override
	public String toString() {
		return String.format("RegisterServiceResponseImpl{" + ok + "}");
	}
}
