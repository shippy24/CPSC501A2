/**
    NAME : SHAYNE MUJURU
    UCID : 30029552
    TITLE: CPSC501 AII : INSPECTOR CLASS 
    DESCRIPTION : Goal is to create a reflective object inspector 
                    that does a complete introspection of 
                    an object at runtime.

**/ 

import java.lang.reflect.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {

        //attaining class object which will be used to get declaring class
        Class objClass = obj.getClass();
        String declClass = objClass.getName();
        System.out.println("--Declaring Class: " + declClass);
    }
}