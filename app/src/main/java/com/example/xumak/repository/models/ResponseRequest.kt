package com.example.xumak.repository.models


data class ResponseRequest<out T>(val status: Boolean, val data: T, val message: String)


