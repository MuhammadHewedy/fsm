package mhewedy.fsm;

import java.util.Arrays;
import java.util.List;

public class OutStatusHolder<T> implements Holder{

	ConditionHolder<T> conditionHolder;
	private T otherwise;

	public OutStatusHolder<T> otherwise(T t) {
		this.otherwise = t;
		return this;
	}

	public T get() {

		T input = conditionHolder.inStatusHolder.input;
		T[] match = conditionHolder.inStatusHolder.match;
		List<Boolean> andList = conditionHolder.andList;
		List<OrCondition> orCondList = conditionHolder.orCondList;
		T then = conditionHolder.then;

		if (!Arrays.asList(match).contains(input)) {
			return null;
		}

		for (Boolean bool : andList) {
			if (!bool) {
				return otherwise;
			}
		}

		for (OrCondition cond : orCondList) {
			if (!cond.evaluate()) {
				return otherwise;
			}
		}

		return then;
	}

}
