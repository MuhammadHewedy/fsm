package mhewedy.fsm.holders;

public class InStatusHolder<T> {

	T input;
	T[] match;

	public void setInput(T input) {
		this.input = input;
	}

	public ConditionHolder<T> in(
			@SuppressWarnings("unchecked") T... matchStatus) {

		this.match = matchStatus;
		ConditionHolder<T> conditionHolder = new ConditionHolder<>();
		conditionHolder.inStatusHolder = this;
		return conditionHolder;
	}
}