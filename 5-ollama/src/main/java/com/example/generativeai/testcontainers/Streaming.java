package com.example.generativeai.testcontainers;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@Slf4j
public class Streaming {


    public static void main(String[] args) {
        OllamaContainer ollamaContainer = new OllamaContainer(
                DockerImageName.parse("ilopezluna/llama3.2:0.3.13-1b").asCompatibleSubstituteFor("ollama/ollama"))
                .withReuse(true);
        ollamaContainer.start();

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl(ollamaContainer.getEndpoint())
                .modelName("llama3.2:3b")
                .build();
        String answer = model.generate("Provide 3 short bullet points explaining why Java is awesome");
        log.info("Response from LLM (\uD83E\uDD16)-> {}", answer);
    }

}
