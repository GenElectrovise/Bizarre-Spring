package genelectrovise.bizarre.spring.server.cmd.register;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import genelectrovise.bizarre.spring.api.KeyPair;

@Component
public class KeyRegister {

	private static final int HASH_REPEATS = 10;

	BiMap<String, KeyPair> keys;

	public KeyRegister() { this.keys = HashBiMap.create(); }

	public KeyRegister(BiMap<String, KeyPair> keys) { this.keys = keys; }

	/**
	 * @param name The name of the service to associate with this {@link KeyPair}
	 * @param pair The {@link KeyPair} to register
	 * @return The given {@link KeyPair}
	 */
	public KeyPair registerKeyPair(String name, KeyPair pair) {
		keys.put(name, pair);
		return pair;
	}

	/**
	 * @return A new {@link KeyPair}
	 */
	public KeyPair generateKeyPair() {

		String parentChildKey = hashCode() + "0";
		String childParentKey = parentChildKey.hashCode() + "0";

		HashFunction func = Hashing.sha256();

		for (int i = 0; i < HASH_REPEATS; i++) {
			parentChildKey = func.hashString(parentChildKey, StandardCharsets.UTF_8).toString();
		}

		for (int i = 0; i < HASH_REPEATS; i++) {
			childParentKey = func.hashString(childParentKey.toString(), StandardCharsets.UTF_8).toString();
		}

		return new KeyPair(parentChildKey, childParentKey);
	}

	public boolean verify(String serviceName, String keyIn) { 
		KeyPair pair = keys.get(serviceName);
		for (String keyTest : Lists.newArrayList(pair.getChildParentKey(), pair.getParentChildKey())) {
			if(keyTest.equals(keyIn)) {
				return true;
			}
		}
		
		return false;
			
	 }

}
