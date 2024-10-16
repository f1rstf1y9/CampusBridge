package com.tnasfer.gbict;

import org.hibernate.validator.internal.constraintvalidators.bv.DigitsValidatorForCharSequence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class GbictApplicationTests {

	@Test
	void contextLoads() {
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		public DigitsValidatorForCharSequence digitsValidatorForCharSequence() {
			return new DigitsValidatorForCharSequence();
		}
	}

}
