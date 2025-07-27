package com.aydanjb.backpicker.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String testPage() {
        return "<h1>Hello from Backpicker!</h1><p>This is just a test.</p>";
    }

    @GetMapping("/secure")
    public String securePage() {
        return "<h1>This is a secure page.</h1>";
    }
}
