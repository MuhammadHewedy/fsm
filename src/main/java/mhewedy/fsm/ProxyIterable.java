package mhewedy.fsm;

import java.util.Collection;
import java.util.Iterator;

public class ProxyIterable<T> implements Iterable<T> {

	private Collection<T> list;

	ProxyIterable(Collection<T> list) {
		if (list == null) {
			throw new IllegalArgumentException("list is null");
		}
		this.list = list;
	}

	/**
	 * 
	 * @return reference to the wrapped list
	 */
	public Collection<T> getList() {
		return list;
	}

	public <F> boolean each(String fieldName, Predicate<F> toApply) {
		return list.size() == applyCount(fieldName, toApply);
	}

	public <F> boolean any(String fieldName, Predicate<F> toApply) {
		return applyCount(fieldName, toApply) >= 1;
	}

	public <F> boolean none(String fieldName, Predicate<F> toApply) {
		return applyCount(fieldName, toApply) == 0;
	}

	public <F> boolean count(int count) {
		return this.list.size() == count;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	// -- private

	@SuppressWarnings("unchecked")
	private <F> int applyCount(String fieldName, Predicate<F> toApply) {
		int trueCount = 0;

		Iterator<T> iterator = this.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			trueCount += toApply.apply((F) new ProxyObject<T>(next)
					.get(fieldName)) == true ? 1 : 0;
		}
		return trueCount;
	}
}