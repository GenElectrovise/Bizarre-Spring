package genelectrovise.bizarre.spring.server.cmd.register;

public class FailedHandshakeException extends RuntimeException {
	public FailedHandshakeException() { super(); }

	public FailedHandshakeException(String message) { super(message); }

	public FailedHandshakeException(String message, Throwable cause) { super(message, cause); }

	public FailedHandshakeException(Throwable cause) { super(cause); }
}
