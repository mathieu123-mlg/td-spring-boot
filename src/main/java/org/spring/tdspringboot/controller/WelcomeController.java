package org.spring.tdspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(value = "name") String name) {
        if (name.isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body("Name is required and cannot be empty");
        }
        return ResponseEntity
                .status(200)
                .body("Welcome " + name);
    }
}
