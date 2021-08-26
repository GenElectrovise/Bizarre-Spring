package genelectrovise.bizarre.spring.api;

public class RegisterServiceResponse {

	String parentChildKey;
	String childParentKey;
	String host;
	int port;
	String type;

	public RegisterServiceResponse(String parentChildKey, String childParentKey, String host, int port, String type) {
		this.parentChildKey = parentChildKey;
		this.childParentKey = childParentKey;
		this.host = host;
		this.port = port;
		this.type = type;
	}

	public String getParentChildKey() { return parentChildKey; }

	public String getChildParentKey() { return childParentKey; }

	public String getHost() { return host; }

	public int getPort() { return port; }

	public String getType() { return type; }

	public String toString() { return getClass().getSimpleName() + "{" + "parentChildKey=" + parentChildKey + " childParentKey=" + childParentKey + " host=" + host + " port=" + port + " type=" + type + "}"; }

}