package genelectrovise.bizarre.spring.server.cmd.register;

public class InvalidRegistrationPacketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object key, value;

	public InvalidRegistrationPacketException(String message, String key, Object value) { super(message); }

	public Object getKey() { return key; }

	public Object getValue() { return value; }

}
