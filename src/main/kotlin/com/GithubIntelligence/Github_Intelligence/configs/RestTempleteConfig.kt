package com.GithubIntelligence.Github_Intelligence.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTempleteConfig {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}