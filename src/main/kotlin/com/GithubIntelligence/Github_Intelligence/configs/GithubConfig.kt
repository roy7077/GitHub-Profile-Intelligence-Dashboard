package com.GithubIntelligence.Github_Intelligence.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "github")
class GitHubConfig {
    lateinit var baseUrl: String
    lateinit var token: String
}
