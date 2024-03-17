package com.jetbrains.kmpapp.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.TodoItem
import com.jetbrains.kmpapp.interfaces.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel(private val testRepository: TestRepository) : ScreenModel {

    private val _museums = MutableStateFlow<List<TodoItem>>(emptyList())
    val museums: StateFlow<List<TodoItem>> = _museums.asStateFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    fun getData() {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                _museums.value = testRepository.getData()
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}
