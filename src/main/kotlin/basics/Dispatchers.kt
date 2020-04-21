package basics

import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
fun main() {
    runBlocking {
//        launch(Dispatchers.Main) {
//            println("Main Disptacher. Thread: ${Thread.currentThread().name}")
//        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined One. Thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined Two. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default Dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO Dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("My Thread")) {
            println("newSingleThreadContext Thread: ${Thread.currentThread().name}")
        }
    }
}

