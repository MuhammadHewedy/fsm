package mhewedy.fsm.test;

import static mhewedy.fsm.Manager.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MyTestCase {

	List<Student> list = new ArrayList<>();
	City city = new City("Cairo");

	@Before
	public void setUp() throws Exception {
		list.add(new Student(100));
		list.add(new Student(90));
		list.add(new Student(80));
	}

	@Test
	public void testOfProxyObjectCache() {

		City cairo = new City("Cairo");
		City london = new City("London");

		assertEquals(of(cairo), of(cairo));
		assertEquals(of(london), of(london));
		assertNotEquals(of(cairo), of(london));
	}
	
	@Test
	public void testOfProxyListCache() {
		
		List<Student> stdList1 = new ArrayList<>();
		stdList1.add(new Student(100));
		stdList1.add(new Student(90));
		
		List<Student> stdList2 = new ArrayList<>();
		stdList2.add(new Student(10));
		stdList2.add(new Student(90));
		
		assertEquals(of(stdList1), of(stdList1));
		assertEquals(of(stdList2), of(stdList2));
		assertNotEquals(of(stdList1), of(stdList2));
		
	}
	
	@Test
	public void testGetNull() {
		Status currentStatus = Status.STATE_A;
		Status outState = 
				given(currentStatus)
				.in(Status.STATE_B, Status.STATE_C)
				.then(Status.STATE_D)
				.get();
		
		assertNull(outState);
	}
	
	@Test
	public void testWhenOfListAny(){
		
		Status currentStatus = Status.STATE_A;
		
		Status out =
				given(currentStatus)
				.in(Status.STATE_A, Status.STATE_B)
				.when(of(list).any("grade", equal(100)))
				.then(Status.STATE_D)
				.get();
		
		assertEquals(out, Status.STATE_D);
	}
	
	@Test
	public void testWhenOfListFilterSize() {

		Status currentStatus = Status.STATE_A;

		Status out = 
				given(currentStatus)
				.in(Status.STATE_A, Status.STATE_B)
				.when(of(list).filter("grade", less(100)).size() == 2)
				.then(Status.STATE_D)
				.get();

		assertEquals(out, Status.STATE_D);
	}
	
	@Test
	public void testWhenOfListOrCondition(){
		Status currentStatus = Status.STATE_A;
		
		Status out = 
				given(currentStatus)
				.in(Status.STATE_A, Status.STATE_B)
				.when(of(city).get("location").equals("Cairo"))
				.and(or(of(list).any("grade", equal(100)))
						.or(city.location.equals("Riyadh")))
				.then(Status.STATE_D)
				.get();
		
		assertEquals(out, Status.STATE_D);
	}
	
	@Test
	public void testOtherwise(){
		
		Status currentStatus = Status.STATE_A;
		
		Status out = 
				given(currentStatus)
				.in(Status.STATE_A, Status.STATE_B)
				.when(of(city).get("location").equals("Alex"))
				.and(or(of(list).any("grade", equal(100)))
						.or(city.location.equals("Riyadh")))
				.then(Status.STATE_D)
				.otherwise(Status.STATE_A)
				.get();
		
		assertEquals(out, Status.STATE_A);
		
	}
	

	// ------------- some data structure

	enum Status {
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
