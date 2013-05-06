package com.lyw.test;

import junit.framework.TestCase;

public class AddOperationTest extends TestCase{

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    public void testAdd() {
        System.out.println("add");
        int x = 1;
        int y = 0;
        AddOperation instance = new AddOperation();
        int expResult = 1;
        int result = instance.add(x, y);
        assertEquals(expResult, result);
    }
}