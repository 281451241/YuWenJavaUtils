package com.lyw.util;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTextCase extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public final void test() {
		TextCase tc = new TextCase();
		assertEquals('g', tc.UpToLowerCase('G'));
	}

}
