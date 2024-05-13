package com.footballmatches.matches.exceptions;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler extends Exception {
}
