package pl.gornicki.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    @GetMapping
    public String hello() {
        return "Hello from post controller";
    }
}
