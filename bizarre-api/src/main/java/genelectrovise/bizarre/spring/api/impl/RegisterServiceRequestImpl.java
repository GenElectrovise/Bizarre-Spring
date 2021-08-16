package genelectrovise.bizarre.spring.api.impl;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.ServiceType;

public class RegisterServiceRequestImpl implements RegisterServiceRequest {
	
	protected ServiceType serviceType;
	protected String serviceHost;
	protected int servicePort;

	public RegisterServiceRequestImpl(ServiceType serviceType, String serviceHost, int servicePort) {
		this.serviceType = serviceType;
		this.serviceHost = serviceHost;
		this.servicePort = servicePort;
	}

	@Override
	public ServiceType getServiceType() {
		return serviceType;
	}

	@Override
	public String getServiceHost() {
		return serviceHost;
	}

	@Override
	public int getServicePort() {
		return servicePort;
	}
	
	@Override
	public String toString() {
		return serviceHost + ":" + servicePort + " (" + serviceType + ")";
	}
	
}
