package mhewedy.fsm;

import java.util.Collection;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ProxyIterable<T> implements Iterable<T> {

	private Collection<T> collection;

	/**
	 * Never use this constructor directly, use
	 * {@code Manager#of(java.util.List)} instead.
	 * 
	 * @param c
	 */
	ProxyIterable(Collection<T> c) {
		if (c == null) {
			throw new IllegalArgumentException("collection is null");
		}
		this.collection = c;
	}

	/**
	 * 
	 * @return reference to the wrapped collection
	 */
	public Collection<T> get() {
		return collection;
	}

	// -- method to check field against predicate

	public <F> boolean each(String fieldName, Predicate<F> toApply) {
		return collection.size() == getCount(fieldName, toApply);
	}

	public <F> boolean any(String fieldName, Predicate<F> toApply) {
		return getCount(fieldName, toApply) >= 1;
	}

	public <F> boolean none(String fieldName, Predicate<F> toApply) {
		return getCount(fieldName, toApply) == 0;
	}

	// -- method to filter based on field predicate

	public <F> Collection<T> filter(String fieldName, Predicate<F> toApply) {
		try {
			Collection<T> t = collection.getClass().newInstance();

			Iterator<T> iterator = this.iterator();
			while (iterator.hasNext()) {
				T next = iterator.next();
				if (toApply.apply((F) Manager.of(next).get(fieldName))) {
					t.add(next);
				}
			}
			return t;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return collection.iterator();
	}

	// -- private
	private <F> int getCount(String fieldName, Predicate<F> toApply) {
		int trueCount = 0;

		Iterator<T> iterator = this.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			trueCount += toApply.apply((F) Manager.of(next).get(fieldName)) == true ? 1
					: 0;
		}
		return trueCount;
	}
}