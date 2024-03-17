package com.jetbrains.kmpapp.interfaces

import com.jetbrains.kmpapp.data.TodoItem

interface TestRepository {
    suspend fun getData(): List<TodoItem>
}