package com.jetbrains.kmpapp.repository

import com.jetbrains.kmpapp.data.TodoItem
import com.jetbrains.kmpapp.interfaces.TestRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException

class TestRepositoryImpl(private val httpClient: HttpClient) : TestRepository {
    override suspend fun getData(): List<TodoItem> {
        return try {
//            httpClient.get(BASE_URL).body()
            httpClient.get("https://jsonplaceholder.typicode.com/todos").body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }
}