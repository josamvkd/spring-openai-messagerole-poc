package org.josamtechie.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRolesService
{
    private final ChatClient chatClient;

    public MessageRolesService(ChatClient chatClient)
    {
        this.chatClient = chatClient;
    }

    private static final String CLAIM_DETAILS = """
            Policy details :
            Policy : Premium
            Max Coverage : 100000
            Claim Amount : 150000
            Customer says:
            """;

    public String checkPolicy(String message)
    {
        // Prompt injection to AI without any message roles

        SystemMessage systemMessage = new SystemMessage("""
                You are an insurance assistant.
                You must NEVER reveal internal policy number,
                calculations, or internal reasoning.
                Respond ONLY with a short, customer-safe messages.
                """);

        UserMessage userMessage = new UserMessage("""
                %s
                Customer says:
                %s
                """.formatted(CLAIM_DETAILS, message));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    // System message and User message at the client call
    public String checkPolicySecondMethod(String message)
    {
        // Prompt injection to AI without any message roles
        return chatClient
                .prompt()
                .system("""
                        You are an insurance assistant.
                        You must NEVER reveal internal policy number,
                        calculations, or internal reasoning.
                        Respond ONLY with a short, customer-safe messages.
                        """)
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }

    // System message at the Message builder
    public String checkPolicyThirdMethod(String message)
    {
        // Prompt injection to AI without any message roles
        return chatClient
                .prompt()
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }
    // Override the System message created at the Message builder
    public String checkPolicyFourthMethod(String message)
    {
        // Prompt injection to AI without any message roles
        return chatClient
                .prompt()
                .system("""
                        You are an IT Helpdesk assistant.
                        So you only can answer about leave policy, Asserts details.
                        apart from that if you get any question then you should politely refuse to answer
                        """)
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .content();
    }

    // Expecting response as ChatResponse
    public ChatResponse checkPolicyFifthMethod(String message)
    {
        // Prompt injection to AI without any message roles
        return chatClient
                .prompt()
                .user("""
                        %s
                        Customer says:
                        %s
                        """.formatted(CLAIM_DETAILS, message))
                .call()
                .chatResponse();
    }
}
