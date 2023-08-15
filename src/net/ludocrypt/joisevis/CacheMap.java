package net.ludocrypt.joisevis;

import java.util.HashMap;
import java.util.function.Function;

public class CacheMap<K, V> extends HashMap<K, V> {

	private final Function<K, V> valueSupplier;

	public CacheMap(Function<K, V> valueSupplier) {
		this.valueSupplier = valueSupplier;
	}

	@Override
	public V get(Object key) {
		V value = super.get(key);

		if (value == null) {
			value = valueSupplier.apply((K) key);
			put((K) key, value);
		}

		return value;
	}

}
