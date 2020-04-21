package basics

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Unconfined

var functionCalls = 0

fun main() {

    runBlocking {
        val job1 = launch {

            println("Job One Launched")
            val job2 = launch {
                println("Job Two Launched")
                delay(3000L)
                println("Job Two Finishing")
            }
            job2.invokeOnCompletion { println("Job Two Completed") }
        }
        delay(500L)
        job1.invokeOnCompletion { println("Job One Completed") }
        println("Cancelling Job One")
        job1.cancel()
    }

}