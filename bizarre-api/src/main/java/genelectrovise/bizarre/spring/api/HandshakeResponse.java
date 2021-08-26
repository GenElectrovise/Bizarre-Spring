package genelectrovise.bizarre.spring.api;

public class HandshakeResponse {

	int result;
	String cpKey;

	public HandshakeResponse(int result, String cpKey) {
		this.result = result;
		this.cpKey = cpKey;
	}

	public int getResult() { return result; }

	public void setResult(int result) { this.result = result; }

	public String getCpKey() { return cpKey; }

	public void setCpKey(String cpKey) { this.cpKey = cpKey; }

	public String toString() { return getClass().getSimpleName() + "{" + "result=" + result + " cpKey=" + cpKey + "}"; }

}