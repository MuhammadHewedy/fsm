package mhewedy.fsm;

import java.util.List;

import mhewedy.fsm.holders.InStatusHolder;

public class Manager {

	public static <T> ProxyObject<T> of(T o) {
		return new ProxyObject<T>(o);
	}

	public static <T> ProxyIterable<T> of(List<T> list) {
		return new ProxyIterable<T>(list);
	}

	public static <T> Predicate<T> equal(T t) {
		return new Predicate.EqualPredicate<T>(t);
	}

	public static <T extends Comparable<T>> Predicate<T> greater(T t) {
		return new Predicate.GreaterPredicate<T>(t);
	}

	public static <T extends Comparable<T>> Predicate<T> less(T t) {
		return new Predicate.LessPredicate<T>(t);
	}

	public static <E extends Enum<?>> InStatusHolder<E> from(E currentStatus) {
		InStatusHolder<E> holder = new InStatusHolder<>();
		holder.setInput(currentStatus);
		return holder;
	}

	public static OrCondition or(boolean test) {
		OrCondition orCondition = new OrCondition();
		orCondition.boolList.add(test);
		return orCondition;
	}

}
