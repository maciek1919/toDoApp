package com.example.todoapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    var todoItems = mutableStateListOf<TodoItem>()
        private set


    fun addItem(item: TodoItem){
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem){
        todoItems.remove(item)
    }
}