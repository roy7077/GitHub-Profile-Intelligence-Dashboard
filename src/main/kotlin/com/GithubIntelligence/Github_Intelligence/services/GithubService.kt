package com.GithubIntelligence.Github_Intelligence.services

import com.GithubIntelligence.Github_Intelligence.dtos.FollowersDTO
import com.GithubIntelligence.Github_Intelligence.dtos.FollowingDTO
import com.GithubIntelligence.Github_Intelligence.dtos.UserSummaryDTO
import com.GithubIntelligence.Github_Intelligence.repositories.GithubRepo
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
class GithubService(
    val githubRepo: GithubRepo,
    val redisService: RedisService,
    val mapper: ObjectMapper
) {
    public fun summary(username: String): UserSummaryDTO? {
        val cache = redisService.getData("summary-$username")
        if (cache != null) {
            println("From cache")
            val user = mapper.convertValue(cache, UserSummaryDTO::class.java)
            return user
        }

        val result = githubRepo.summary(username)
        if (result != null) {
            redisService.saveData("summary-$username", result)
        }
        println("from Github")
        return result
    }


    public fun listOfFollowers(username: String): List<FollowersDTO> ? {
        val cache = redisService.getData("followers-$username")
        if (cache != null) {
            println("From cache")
            val user: List<FollowersDTO> = mapper.convertValue(
                cache,
                object : com.fasterxml.jackson.core.type.TypeReference<List<FollowersDTO>>() {}
            )
            return user
        }

        val result = this.githubRepo.listOfFollowers(username)
        if (result != null && result.isNotEmpty()) {
            redisService.saveData("followers-$username", result)
        }
        println("from Github")
        return result
    }

    public fun listOfFollowing(username: String): List<FollowingDTO> ? {
        val cache = redisService.getData("following-$username")
        if (cache != null) {
            println("From cache")
            val user: List<FollowingDTO> = mapper.convertValue(
                cache,
                object : com.fasterxml.jackson.core.type.TypeReference<List<FollowingDTO>>() {}
            )
            return user
        }

        val result = this.githubRepo.listOfFollowing(username)
        if (result != null && result.isNotEmpty()) {
            redisService.saveData("following-$username", result) // Cache it for next time
        }
        println("from Github")
        return result
    }


}