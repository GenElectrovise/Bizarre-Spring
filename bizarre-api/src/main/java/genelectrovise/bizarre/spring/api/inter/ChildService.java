package genelectrovise.bizarre.spring.api.inter;

public class ChildService {

	String host;
	int port;
	String name;

	public ChildService(String host, int port, String name) {
		this.host = host;
		this.port = port;
		this.name = name;
	}

	public String getHost() { return host; }

	public int getPort() { return port; }

	public String getName() { return name; }

}