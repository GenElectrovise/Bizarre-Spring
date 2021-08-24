package genelectrovise.bizarre.spring.api.inter;

public class HandshakeRequest {

	String type;
	String host;
	int port;

	public HandshakeRequest(String type, String host, int port) {
		this.type = type;
		this.host = host;
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String toString() {
		return getClass().getSimpleName() + "{" + "type=" + type + " host=" + host + " port=" + port + "}";
	}

}