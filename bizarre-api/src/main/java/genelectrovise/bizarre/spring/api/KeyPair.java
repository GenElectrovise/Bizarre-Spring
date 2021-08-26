package genelectrovise.bizarre.spring.api;

public class KeyPair {

	String parentChildKey;

	String childParentKey;

	public KeyPair(String parentChildKey, String childParentKey) {
		this.parentChildKey = parentChildKey;
		this.childParentKey = childParentKey;
	}

	public String getParentChildKey() { return parentChildKey; }

	public String getChildParentKey() { return childParentKey; }

	@Override
	public String toString() { return "KeyPair{" + "parentChildKey=" + parentChildKey + " childParentKey=" + childParentKey + "}"; }
}