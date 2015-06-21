package mhewedy.fsm;

import java.util.ArrayList;
import java.util.List;

public class ConditionHolder<T> implements Holder{

	InStatusHolder<T> inStatusHolder;
	T then;

	List<OrCondition> orCondList = new ArrayList<>();
	List<Boolean> andList = new ArrayList<>();

	public ConditionHolder<T> when(OrCondition b) {
		orCondList.add(b);
		return this;
	}

	public ConditionHolder<T> and(OrCondition b) {
		orCondList.add(b);
		return this;
	}

	public ConditionHolder<T> when(boolean b) {
		andList.add(b);
		return this;
	}

	public ConditionHolder<T> and(boolean b) {
		andList.add(b);
		return this;
	}

	public OutStatusHolder<T> then(T t) {
		this.then = t;
		OutStatusHolder<T> outStatusHolder = new OutStatusHolder<>();
		outStatusHolder.conditionHolder = this;
		return outStatusHolder;
	}

}
