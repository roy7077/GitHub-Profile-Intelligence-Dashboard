package com.GithubIntelligence.Github_Intelligence.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory?): RedisTemplate<String?, Any?> {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        val template = RedisTemplate<String?, Any?>()
        template.setConnectionFactory(factory)
        template.setValueSerializer(GenericJackson2JsonRedisSerializer(mapper))
        template.setKeySerializer(StringRedisSerializer())
        return template
    }
    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        return mapper
    }

}
