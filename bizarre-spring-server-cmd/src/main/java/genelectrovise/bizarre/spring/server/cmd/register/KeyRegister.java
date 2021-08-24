package genelectrovise.bizarre.spring.server.cmd.register;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import genelectrovise.bizarre.spring.api.inter.KeyPair;

public class KeyRegister {

	BiMap<String, KeyPair> keys;

	public KeyRegister(BiMap<String, KeyPair> keys) {
		this.keys = keys;
	}

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

		String parentChildKey = hashCode() + ".";
		String childParentKey = hashCode() + ".";

		for (String str : List.of(parentChildKey, childParentKey)) {
			HashCode hash = Hashing.sha256().hashString(str + LocalDateTime.now().toString(), StandardCharsets.UTF_8);
			str = hash.toString();
		}

		return new KeyPair(parentChildKey, childParentKey);
	}

}
