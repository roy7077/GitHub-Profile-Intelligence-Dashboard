package com.GithubIntelligence.Github_Intelligence.services

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun saveData(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun getData(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    fun deleteData(key: String) {
        redisTemplate.delete(key)
    }

    @Scheduled(cron = "0 * * * * *")
    fun eraseCurrentDb() {
        val connection = redisTemplate.connectionFactory?.connection
        connection?.flushDb()
    }
}
