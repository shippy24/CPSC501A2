/**
    NAME : SHAYNE MUJURU
    UCID : 30029552
    TITLE: CPSC501 AII : INSPECTOR CLASS 
    DESCRIPTION : Goal is to create a reflective object inspector 
                    that does a complete introspection of 
                    an object at runtime.

**/ 

import java.lang.reflect.*;
import java.util.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {

        //attaining class object which will be used to get declaring class
        Class objClass = obj.getClass();
        String declClass = objClass.getName();
        System.out.println("--Declaring Class: " + declClass);

        //immediate super class
        String superClass = objClass.getSuperclass().getName();
        System.out.println("--Super Class: " + superClass);

        //Interfaces the class implements
        traverseInterfaces(objClass);
        System.out.println();

        //attaining methods for current object and super class 
        System.out.println("\n-----  Base class methods --------");
        inspectMethods(objClass);
        System.out.println("\n-----  Traversing Super Classes --------");
        traverseSuperMethods(objClass);

        
    }

    public void traverseSuperMethods(Class objClass){
        System.out.println("\n-----  Base Super Class --------");
        inspectMethods(objClass.getSuperclass());
        Class currentSuperClass = objClass.getSuperclass();

        if (currentSuperClass.getSuperclass() != null)
            System.out.println("\n-----  Recursive Super Classes --------");

        while (currentSuperClass.getSuperclass() != null) {
            Class nextSuperClass = currentSuperClass.getSuperclass();
            System.out.println("\n----- " +  nextSuperClass.getName() + " --------");
            inspectMethods(nextSuperClass);
            currentSuperClass = nextSuperClass;
        }

        System.out.println("\n-----   End of super class traversal    -----\n");
    }

    public void traverseInterfaces(Class objClass){
        Class[] interfaces = objClass.getInterfaces();
        //Print the name of each of the interfaces
        for (Class interFace : interfaces) {
            System.out.println("\n--Interface : " + interFace.getName());

            //Get interface methods
            if (interFace.getMethods().length != (new Method[] {}).length){
                System.out.println("\n-----  Interface methods --------");
                inspectMethods(interFace);
            }
            if (interFace.getInterfaces().length != (new Method[] {}).length) {
                System.out.println("\n-----  Recursive Super Interfaces --------");
            }

            while (interFace.getInterfaces().length != (new Method[] {}).length) {
                for (Class recInterface : interFace.getInterfaces()) {
                    traverseInterfaces(recInterface);
                }
            }
            System.out.println("\n----- End of Interface traversal  -----\n");
        }

    }

    public void inspectMethods(Class objClass) {
        //Get all the declared methods
        Method[] declMethods = objClass.getDeclaredMethods();
        Class[] exceptions;
        Class[] params;

        //Print info about the methods
        for (Method method : declMethods) {
            System.out.println("--- Method: " + method.getName());

            //Get exceptions
            exceptions = method.getExceptionTypes();
            for (Class exception : exceptions) {
                System.out.println("---     Exception : " + exception.getName());
            }

            //Get parameter types
            params = method.getParameterTypes();
            for (Class param : params) {
                System.out.println("---     Parameter : " + param.getName());
            }

            //Get Return type
            System.out.println("---     Return type : " + method.getReturnType().getName());

            //Get Modifiers
            System.out.println("---     Modifiers : " + method.getModifiers());
            
            System.out.println();
        }
    }
}