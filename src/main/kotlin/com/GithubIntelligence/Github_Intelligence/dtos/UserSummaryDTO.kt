package com.GithubIntelligence.Github_Intelligence.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class UserSummaryDTO(
    var login: String? = null,
    var id: Int = 0,
    var avatar_url: String? = null,
    var name: String? = null,
    var company: String? = null,
    var blog: String? = null,
    var location: String? = null,
    var email: String? = null,
    var bio: String? = null,
    var public_repos: Int = 0,
    var followers: Int = 0,
    var following: Int = 0,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    var created_at: Date? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    var updated_at: Date? = null
)
