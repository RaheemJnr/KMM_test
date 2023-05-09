package com.example.multi_test

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform