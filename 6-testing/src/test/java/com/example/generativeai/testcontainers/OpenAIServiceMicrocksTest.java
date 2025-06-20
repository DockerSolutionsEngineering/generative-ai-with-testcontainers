package com.example.generativeai.testcontainers;

import io.github.microcks.testcontainers.MicrocksContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
class OpenAIServiceMicrocksTest {
    static String microcksUrl;
    static String question = "In F1 2024 season which driver won the Great Britain Grand Prix?";

    @Container
    static MicrocksContainer microcks = new MicrocksContainer("quay.io/microcks/microcks-uber:latest")
            .withMainArtifacts("openai-api-mock.yaml");

    @BeforeAll
    static void setUp() {
        microcksUrl = microcks.getRestMockEndpoint("OpenAI API", "1.0");
    }

    @Test
    void testChatCompletion() {
        GetAnswerAgent getAnswerAgent = new GetAnswerAgent(microcksUrl, "gpt-3.5-turbo");
        String response = getAnswerAgent.getStraightAnswer(question);
        assertTrue(response.contains("Verstappen"), "Response should contain 'assistant' but was: " + response);
    }

    @Test
    void testModelHallucinate() {
        GetAnswerAgent getAnswerAgent = new GetAnswerAgent(microcksUrl, "hallucinate");
        String response = getAnswerAgent.getStraightAnswer(question);
        assertTrue(response.contains("Leclerc"), "... was: " + response);
    }
}
