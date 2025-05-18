package com.GithubIntelligence.Github_Intelligence.dtos

data class FollowersDTO(
    val login: String? = null,
    val id: Int = 0,
    val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val user_view_type: String? = null,
    val site_admin: Boolean = false
)
