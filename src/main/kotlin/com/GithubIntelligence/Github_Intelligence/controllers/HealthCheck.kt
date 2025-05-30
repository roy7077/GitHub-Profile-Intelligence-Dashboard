package com.GithubIntelligence.Github_Intelligence.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheck {
    @GetMapping
    fun health() : ResponseEntity<String> {
        return ResponseEntity("OK", HttpStatus.OK)
    }
}