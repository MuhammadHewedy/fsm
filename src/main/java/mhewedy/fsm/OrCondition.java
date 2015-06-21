package mhewedy.fsm;

import java.util.ArrayList;
import java.util.List;

public class OrCondition {

	List<Boolean> boolList = new ArrayList<>();

	public OrCondition or(boolean test) {
		boolList.add(test);
		return this;
	}

	boolean evaluate() {
		for (Boolean bool : boolList) {
			if (bool) {
				return true;
			}
		}
		return false;
	}
}
