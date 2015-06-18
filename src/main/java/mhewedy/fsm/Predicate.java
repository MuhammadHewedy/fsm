package mhewedy.fsm;

abstract class Predicate<T> {

	abstract boolean apply(T t);

	static <R> Predicate<R> equal(R t) {
		return new EqualPredicate<R>(t);
	}

	static class EqualPredicate<E> extends Predicate<E> {

		E test;

		public EqualPredicate(E test) {
			this.test = test;
		}

		@Override
		public boolean apply(E t) {
			return t.equals(test);
		}
	}

	static class GreaterPredicate<E extends Comparable<E>> extends Predicate<E> {

		E test;

		public GreaterPredicate(E test) {
			this.test = test;
		}

		@Override
		public boolean apply(E t) {
			return t.compareTo(test) > 0;
		}
	}

	static class LessPredicate<E extends Comparable<E>> extends Predicate<E> {
		E test;

		public LessPredicate(E test) {
			this.test = test;
		}

		@Override
		public boolean apply(E t) {
			return t.compareTo(test) < 0;
		}
	}
}