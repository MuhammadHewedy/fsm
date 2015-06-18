package mhewedy.fsm.test;

import static mhewedy.fsm.Manager.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MyTestCase {

	List<Student> list = new ArrayList<>();
	City city = new City("London");

	@Before
	public void setUp() throws Exception {
		list.add(new Student(100));
		list.add(new Student(90));
		list.add(new Student(80));
	}

	@Test
	public void test1() {
		MyState myState = 
				from(MyState.STATE_A)
				.in(MyState.STATE_B, MyState.STATE_C)
				.then(MyState.STATE_D)
				.get();
		
		assertNull(myState);
	}

	// ------------- some data structure

	enum MyState {
		STATE_A, STATE_B, STATE_C, STATE_D
	}

	static class Student {
		int grade;

		Student(int grade) {
			this.grade = grade;
		}
	}

	static class City {
		String location;

		City(String location) {
			this.location = location;
		}
	}
}
