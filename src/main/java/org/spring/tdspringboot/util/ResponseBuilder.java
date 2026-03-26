package org.spring.tdspringboot.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    public ResponseEntity<Object> success(Object body, MediaType mediaType) {
        return ResponseEntity
                .status(200)
                .contentType(mediaType)
                .body(body);
    }

    public ResponseEntity<Object> created(Object body) {
        return ResponseEntity
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    public ResponseEntity<Object> badRequest(String message) {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"" + message + "\"}");
    }

    public ResponseEntity<Object> noContent() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> notSupported() {
        return ResponseEntity
                .status(501)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"Format not supported\"}");
    }

    public ResponseEntity<Object> internalError(String message) {
        return ResponseEntity
                .internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"" + message + "\"}");
    }
}