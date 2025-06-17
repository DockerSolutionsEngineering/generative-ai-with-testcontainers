package com.example.generativeai.testcontainers;

import io.github.microcks.testcontainers.MicrocksContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
class OpenAIServiceMicrocksTest {

    @Container
    static MicrocksContainer microcks = new MicrocksContainer("quay.io/microcks/microcks-uber:latest")
            .withMainArtifacts("openai-api-mock.yaml");

    @BeforeAll
    static void setUp() {
        String microcksUrl = microcks.getRestMockEndpoint("OpenAI API", "1.0");
        new Base(microcksUrl, "gpt-3.5-turbo");
    }

    @Test
    void testChatCompletion() {
        String response = HowTo.getStraightAnswer();
        assertTrue(response.contains("Verstappen"), "Response should contain 'assistant' but was: " + response);
    }
}
