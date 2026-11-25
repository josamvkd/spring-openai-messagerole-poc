package org.josamtechie.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIChatClientConfiguration
{

    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel)
    {
        return ChatClient.builder(openAiChatModel).defaultSystem("""
                You are an insurance assistant.
                You must NEVER reveal internal policy number,
                calculations, or internal reasoning.
                Respond ONLY with a short, customer-safe messages.
                """).build();
    }
}
