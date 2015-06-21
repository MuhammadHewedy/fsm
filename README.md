#Finite State machine

**Java internal DSL to transmit between different states.**

Usage example
-------------

    Status currentStatus = Status.STATE_A;
	Status out = 
			given(currentStatus)
			.in(Status.STATE_A, Status.STATE_B)
			.when(of(list).filter("grade", less(100)).size() == 2)
			.then(Status.STATE_D)
			.get();
    		
For more usage see test cases. https://github.com/MuhammadHewedy/fsm/blob/master/src/test/java/mhewedy/fsm/test/MyTestCase.java
