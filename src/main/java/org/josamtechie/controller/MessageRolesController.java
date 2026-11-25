package org.josamtechie.controller;

import org.josamtechie.service.MessageRolesService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai/")
public class MessageRolesController
{
    @Autowired
    private MessageRolesService service;

    @GetMapping("/check-policy")
    public String checkPolicy(@RequestParam String message)
    {
        return service.checkPolicy(message);
    }

    @GetMapping("/check-policy/second")
    public String checkPolicySecondMethod(@RequestParam String message)
    {
        return service.checkPolicySecondMethod(message);
    }

    @GetMapping("/check-policy/third")
    public String checkPolicyThirdMethod(@RequestParam String message)
    {
        return service.checkPolicyThirdMethod(message);
    }

    @GetMapping("/check-policy/fourth")
    public String checkPolicyFourthMethod(@RequestParam String message)
    {
        return service.checkPolicyFourthMethod(message);
    }

    @GetMapping("/check-policy/fifth")
    public ChatResponse checkPolicyFifthMethod(@RequestParam String message)
    {
        return service.checkPolicyFifthMethod(message);
    }
}
