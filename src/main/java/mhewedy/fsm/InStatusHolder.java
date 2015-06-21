package mhewedy.fsm;


public class InStatusHolder<T> implements Holder{

	T input;
	T[] match;

	public ConditionHolder<T> in(
			@SuppressWarnings("unchecked") T... matchStatus) {

		this.match = matchStatus;
		ConditionHolder<T> conditionHolder = new ConditionHolder<>();
		conditionHolder.inStatusHolder = this;
		return conditionHolder;
	}
}