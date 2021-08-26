package genelectrovise.bizarre.spring.server.gate;

import org.springframework.stereotype.Component;

@Component
public class KeyStorage {

	private String parentChildKey;

	private String childParentKey;

	public KeyStorage() {}

	public void setChildParentKey(String childParentKey) { this.childParentKey = childParentKey; }

	public String getChildParentKey() { return childParentKey; }

	public void setParentChildKey(String parentChildKey) { this.parentChildKey = parentChildKey; }

	public String getParentChildKey() { return parentChildKey; }

	public void storePair(String childParentKey, String parentChildKey) {
		setChildParentKey(childParentKey);
		setParentChildKey(parentChildKey);
	}

	public boolean verify(String key) { return key.equals(parentChildKey) || key.equals(childParentKey); }
}
