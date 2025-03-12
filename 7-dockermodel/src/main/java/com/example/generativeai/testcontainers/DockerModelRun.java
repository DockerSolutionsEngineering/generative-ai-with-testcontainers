package com.example.generativeai.testcontainers;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DockerModelRun {

    // Before running this code, make sure you followed the instructions from the  Customer Zero Release of the Docker Model Runner:
    // https://www.notion.so/dockerinc/Customer-Zero-Release-1af57a1d4673805b8572e61c9cbd45af#1af57a1d4673807bbe0ad575f10fa194
    // You need to:
    // 1. Pull a Model
    //      docker model pull ignaciolopezluna020/llama3.2:1b
    // 2. Use a helper container as a reverse-proxy to use the model from the host with TCP
    //      docker run -d --name model-runner-proxy -p 8080:80 alpine/socat tcp-listen:80,fork,reuseaddr tcp:ml.docker.internal:80


    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080/ml/llama.cpp/v1"; // Local API URL
        String modelName = "ignaciolopezluna020/llama3.2:1b"; // Model name
        String apiKey = "your-api-key"; // If required, otherwise omit

        // Initialize the Langchain4j OpenAI-compatible model
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .apiKey(apiKey) // Remove this line if no API key is needed
                .build();

        // Construct messages
        List<ChatMessage> messages = List.of(
                new SystemMessage("You are a helpful assistant."),
                new UserMessage("Please write 500 words about the fall of Rome.")
        );

        // Get the model's response
        String response = String.valueOf(model.generate(messages));

        // Print the response
        System.out.println("Response from model: " + response);
    }

}
