package com.lyw.web.ognl;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import ognl.Ognl;

import org.junit.Test;

public class OGNLTestCase extends TestCase {  
      
    /** 
     *  
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    @Test  
    public void testGetValue() throws Exception {  
          
        // Create root object  
        User user = new User();  
        user.setId(1);  
        user.setName("downpour");  
  
        // Create context  
        Map context = new HashMap();  
        context.put("introduction","My name is ");  
          
        // Test to directly get value from root object, with no context  
        Object obj = Ognl.parseExpression("name");
        Object name = Ognl.getValue(obj, user);  
        assertEquals("downpour",name);  
          
        // Test to get value(parameter) from context  
        Object contextValue = Ognl.getValue(Ognl.parseExpression("#introduction"), context, user);  
        assertEquals("My name is ", contextValue);  
          
        // Test to get value and parameter from root object and context  
        Object hello = Ognl.getValue(Ognl.parseExpression("#introduction + name"), context, user);  
        assertEquals("My name is downpour",hello);  
                      
    }  
  
    /** 
     *  
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    @Test  
    public void testSetValue() throws Exception {  
        // Create root object  
    	Object obj = new Object();
        User user = new User();  
        user.setId(1);  
        user.setName("downpour");  
          
                // Set value according to the expression  
        Ognl.setValue("user.department.name", obj, "dev");  
        
        System.out.println(((Department)Ognl.getValue("department", obj)).getName());
        assertEquals("dev", user.getDepartment().getName());  
          
    }  
      
  
}  