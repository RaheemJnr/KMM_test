package com.example.multi_test

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    @Throws(Exception::class)
    suspend fun greet(): String {
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎆" +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"


    }

    @Throws(Exception::class)
    suspend fun getImage(): String {
        val image: RandomDog =
            httpClient.get(urlString = "https://dog.ceo/api/breeds/image/random").body()

        return image.message
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}