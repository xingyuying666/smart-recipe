package com.smartrecipe.recipe;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecipeGenerationException.class)
    ResponseEntity<Map<String, String>> handleRecipeGeneration(RecipeGenerationException exception) {
        HttpStatus status = exception.getMessage().contains("尚未配置") ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler({ConstraintViolationException.class, MaxUploadSizeExceededException.class})
    ResponseEntity<Map<String, String>> handleInvalidRequest(Exception exception) {
        return ResponseEntity.badRequest().body(Map.of("message", "图片或偏好内容不符合要求。"));
    }
}
