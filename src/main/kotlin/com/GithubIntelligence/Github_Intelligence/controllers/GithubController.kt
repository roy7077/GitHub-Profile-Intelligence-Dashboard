package com.GithubIntelligence.Github_Intelligence.controllers

import com.GithubIntelligence.Github_Intelligence.services.GithubService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.GithubIntelligence.Github_Intelligence.dtos.ApiResponse
import com.GithubIntelligence.Github_Intelligence.dtos.FollowersDTO
import com.GithubIntelligence.Github_Intelligence.dtos.FollowingDTO
import com.GithubIntelligence.Github_Intelligence.dtos.UserSummaryDTO

@RestController
@RequestMapping("\${app.base-url}")
class GithubController (val githubService: GithubService) {

    // Summary of an user
    @GetMapping("/{username}/summary")
    fun summary(@PathVariable username: String): ResponseEntity<ApiResponse<UserSummaryDTO>> {
        val result: UserSummaryDTO ? = githubService.summary(username)
        if(result!=null){
            return ResponseEntity<ApiResponse<UserSummaryDTO>> (
                ApiResponse<UserSummaryDTO>(true,
                    "Fetched SuccessFully",result), HttpStatus.OK)
        }

        return ResponseEntity<ApiResponse<UserSummaryDTO>> (
            ApiResponse<UserSummaryDTO>(false,
                "User Not Found",null), HttpStatus.NOT_FOUND)
    }

    // List Of Followers Of An User
    @GetMapping("/followers/{username}")
    fun listOfFollowers(@PathVariable username: String): ResponseEntity<ApiResponse<List<FollowersDTO>>> {
        val response: List<FollowersDTO> ? = this.githubService.listOfFollowers(username)
        if(response==null || response.isEmpty()){
            return ResponseEntity<ApiResponse<List<FollowersDTO>>> (ApiResponse<List<FollowersDTO>>(
                false,
                "Followers Not Found",
                null
            ), HttpStatus.NOT_FOUND)
        }
        return ResponseEntity<ApiResponse<List<FollowersDTO>>> (ApiResponse<List<FollowersDTO>>(
            true,
            "List Of Followers Fetched SuccessFully",
            response
        ), HttpStatus.OK)
    }

    // list of following
    @GetMapping("/following/{username}")
    fun listOfFollowing(@PathVariable username: String): ResponseEntity<ApiResponse<List<FollowingDTO>>> {
        val response: List<FollowingDTO> ? = this.githubService.listOfFollowing(username)
        if(response==null || response.isEmpty()){
            return ResponseEntity<ApiResponse<List<FollowingDTO>>> (ApiResponse(
                false,
                "Following Not Found",
                null
            ),HttpStatus.NOT_FOUND)
        }

        return  ResponseEntity<ApiResponse<List<FollowingDTO>>> (ApiResponse(
            true,
            "List Of Following Fetched SuccessFully",
            response
        ),HttpStatus.OK)
    }
}