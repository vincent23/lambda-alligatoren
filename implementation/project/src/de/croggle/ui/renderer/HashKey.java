package de.croggle.ui.renderer;

/**
 * Offers instance-based key objects, which are based on a wrapped objects' identityHashCode.
 * To be used with HashMaps, even if the wrapped class overwrites the equals and hashCode methods.
 * 
 * @param <T> The class of which objects are to be wrapped as keys
 */
public class HashKey<T> {
	private final T key;
	
	/**
	 * Creates a new HashKey with the given object to be used as key instance.
	 * 
	 * @param key the key to be used for comparing and hashing.
	 */
	public HashKey(T key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		int hash = System.identityHashCode(this.key);
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		// See java type erasure. However, we don't even care about the key type.
		HashKey<?> okey = (HashKey<?>) o;
		return okey.key == this.key;
	}
}
