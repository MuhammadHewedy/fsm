package mhewedy.fsm.holders;

import java.util.ArrayList;
import java.util.List;

import mhewedy.fsm.OrCondition;

public class ConditionHolder<T> {

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
