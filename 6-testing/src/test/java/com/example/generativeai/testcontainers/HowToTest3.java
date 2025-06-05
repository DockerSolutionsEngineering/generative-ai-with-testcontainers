package com.example.generativeai.testcontainers;

import com.example.generativeai.testcontainers.agents.ValidatorAgent;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.example.generativeai.testcontainers.Base.chatModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class HowToTest3 {

	static ValidatorAgent validatorAgent;

	final static String reference = """
			- Answer must indicate that Lewis Hamilton won the Great Britain Grand Prix.
			""";

	@BeforeAll
	static void setUp() {
		validatorAgent = AiServices.builder(ValidatorAgent.class).chatLanguageModel(chatModel()).build();
	}

	@Test
	void getStraightAnswer() {
		String straightAnswer = HowTo.getStraightAnswer();
		log.info("Straight Answer: {}", straightAnswer);

		ValidatorAgent.ValidatorResponse validate = validatorAgent.validate(HowTo.question, straightAnswer, reference);
		log.info("Validation: {}", validate);

		assertEquals("yes", validate.response());
	}

	@Test
	void getRaggedAnswer() {
		String raggedAnswer = HowTo.getRaggedAnswer();
		log.info("Ragged Answer: {}", raggedAnswer);

		ValidatorAgent.ValidatorResponse validate = validatorAgent.validate(HowTo.question, raggedAnswer, reference);
		log.info("Validation: {}", validate);

		assertEquals("yes", validate.response());
	}

}