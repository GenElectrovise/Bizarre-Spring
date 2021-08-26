package genelectrovise.bizarre.spring.api;

public class HandshakeRequest {

	int i1;
	int i2;
	String operation;
	String pcKey;

	public HandshakeRequest(int i1, int i2, String operation, String pcKey) {
		this.i1 = i1;
		this.i2 = i2;
		this.operation = operation;
		this.pcKey = pcKey;
	}

	public int getI1() { return i1; }

	public void setI1(int i1) { this.i1 = i1; }

	public int getI2() { return i2; }

	public void setI2(int i2) { this.i2 = i2; }

	public String getOperation() { return operation; }

	public void setOperation(String operation) { this.operation = operation; }

	public String getPcKey() { return pcKey; }

	public void setPcKey(String pcKey) { this.pcKey = pcKey; }

	public String toString() { return getClass().getSimpleName() + "{" + "i1=" + i1 + " i2=" + i2 + " operation=" + operation + " pcKey=" + pcKey + "}"; }

}