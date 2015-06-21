#Finite State machine

**Java internal DSL to transmit between different states.**

Usage example
-------------

    Status out = 
    	  given(currentStatus)
    	  .in(Status.STATE_A, Status.STATE_B)
    	  .when(of(city).get("location").equals("Cairo"))
    	  .and(or(of(list).any("grade", equal(100)))
    	  		.or(city.location.equals("Riyadh")))
    	  .then(Status.STATE_D)
    	  .get();
    		
For more usage see test cases. https://github.com/MuhammadHewedy/fsm/blob/master/src/test/java/mhewedy/fsm/test/MyTestCase.java
