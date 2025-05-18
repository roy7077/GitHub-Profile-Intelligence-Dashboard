package com.GithubIntelligence.Github_Intelligence.dtos

data class ApiResponse<T>(
    val success: Boolean?,
    val message: String?,
    val data: T?
)
