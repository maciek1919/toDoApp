package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoActivityScreen(todoViewModel)
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel){
    TodoScreen(
        items = todoViewModel.todoItems,
        onAddItem = todoViewModel::addItem,
        onRemoveItem = todoViewModel::removeItem,
    )

}


@Composable
fun TodoScreen(
    items: List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,

) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            items(items) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.height(50.dp)
                ) {
                    Text(
                        text = it.task,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(horizontal = 24.dp)

                    )
                    DeleteButton {
                        onRemoveItem(it)
                    }
                }


            }
        }
        InputBox { onAddItem(TodoItem("Task ${kotlin.random.Random.nextInt()}")) }
    }
}




@Composable
fun InputBox(
    add: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxSize()){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround){
            Button(
                onClick = add
            ){
                Text("Add some tasks")
            }
        }

    }
}

@Composable
fun DeleteButton(
    remove: () -> Unit
){
    Button(
        onClick = remove
    ){
        Text(text = "Done")
    }
}