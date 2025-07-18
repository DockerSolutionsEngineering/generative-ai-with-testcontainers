# Generative AI with Testcontainers

This project is Forked from [ilopezluna/generative-ai-with-testcontainers](https://github.com/ilopezluna/generative-ai-with-testcontainers)

It demonstrates how to use Testcontainers to create a seamless development environment for building Generative AI applications.

## Project Structure

1. `1-streaming`: Contains an example of using a gpt language model to generate text in streaming mode.
2. `2-augmented-generation`: Contains an example of augmenting the prompt with additional information to generate more accurate text.
3. `3-embeddings`: Contains an example of generating embeddings from text and calculating similarity between them.
4. `4-rag`: Contains an example of applying RAG (Retrieval-Augmented Generation) to generate better responses.
5. `5-ollama`: Contains an example of using a custom model with ollama with testcontainers
6. `6-testing`: Contains an example of 3 approaches to test Models: String comparison, Vector comparison, Validator agent. Also includes an example of how to mock model responses using Microcks.
7. `7-dockermodel`: Contains an example of interacting with llama3.2 model that is running with Docker Model Runner

## Prerequisites

- Java 21 or higher
- Docker

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/DockerSolutionsEngineering/generative-ai-with-testcontainers.git
    cd generative-ai-with-testcontainers
    ```

2. Build the project:
    ```sh
    ./gradlew build
    ```
3. For modules 1-4: Create .env file in the root of the project and add your OPENAI_API_KEY there:
   ``` OPENAI_API_KEY=your_key_value_starting_from_sk-proj-...```

## Running the Examples

To run the examples, navigate to the desired directory and run the `run` task. For example, to run the `1-streaming` example:

```sh
cd 1-streaming
../gradlew run
