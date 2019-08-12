package com.mimumi.lemonserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class LemonServerApplicationTests {

	@Test
	public void contextLoads() {


	}

}
