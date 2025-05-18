package com.GithubIntelligence.Github_Intelligence.repositories

import GitHubUserDTO
import com.GithubIntelligence.Github_Intelligence.configs.GitHubConfig
import com.GithubIntelligence.Github_Intelligence.dtos.FollowersDTO
import com.GithubIntelligence.Github_Intelligence.dtos.FollowingDTO
import com.GithubIntelligence.Github_Intelligence.dtos.UserSummaryDTO
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Repository
class GithubRepo(
    private val gitHubConfig: GitHubConfig,
    private val restTemplate: RestTemplate
) {

   // private val logger = LoggerFactory.getLogger(GithubRepo::class.java)

    fun summary(username: String): UserSummaryDTO? {
        val url = "${gitHubConfig.baseUrl}/users/$username"

        val headers = buildAuthHeaders()
        val requestEntity = HttpEntity<String>(headers)

        return try {
            val response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                UserSummaryDTO::class.java
            )

            print(response)

            if (response.statusCode == HttpStatus.OK) {
                print(response.body)
                return response.body
            } else {
              //  logger.warn("GitHub API returned status: ${response.statusCode}")
                return null
            }
        } catch (ex: RestClientException) {
            println(ex.message)
            //logger.error("Error fetching GitHub user data: ${ex.message}")
            null
        }
    }


    /**
     * List of follwers of an user
     */
    fun listOfFollowers(username: String): List<FollowersDTO>? {
        val url = "${gitHubConfig.baseUrl}/users/$username/followers"
        val headers = buildAuthHeaders()
        val requestEntity = HttpEntity<String>(headers)
        val response = this.restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            object : ParameterizedTypeReference<List<FollowersDTO>>() {}
        )

        if(response.statusCode == HttpStatus.OK) {
            return response.body
        }

        return null
    }

    /**
     * List of following of an user
     */
    fun listOfFollowing(username: String): List<FollowingDTO>? {
        val url = "${gitHubConfig.baseUrl}/users/$username/following"
        val headers = buildAuthHeaders()
        val requestEntity = HttpEntity<String>(headers)
        val response = this.restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            object : ParameterizedTypeReference<List<FollowingDTO>>() {}
        )

        if(response.statusCode == HttpStatus.OK) {
            return response.body
        }
        return null
    }

    /**
     * Builds HTTP headers with Authorization and Accept values.
     */
    private fun buildAuthHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            setBearerAuth(gitHubConfig.token)
            accept = listOf(MediaType.valueOf("application/vnd.github+json"))
        }
    }
}
