package com.festi.bulle.utils;

import org.springframework.http.ResponseEntity;

public final class Errors {
    public static ResponseEntity<?> badRequest(Exception e) {
        return ResponseEntity.status(400).body(new String[]{e.getMessage()});
    }
}
