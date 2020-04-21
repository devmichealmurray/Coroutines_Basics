package async_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        sendNumbers().collect {
            println("Received $it")
        }
        val numbersFlow = sendNewNumbers()
        val cancelNumbers = sendCancelNumbers()
        println("Flow has not started")
        print("Receiving Numbers.")
        repeat(5) {
            delay(500L)
            print(".")
        }
        println()
        numbersFlow.collect { print("$it  ") }
        println()
        println("Will Timeout in One Second")
        withTimeoutOrNull(1000L) {
            cancelNumbers.collect { println(it)}
        }
    }

}

fun sendNumbers()
        = flowOf("One", "Two", "Three")

//        = listOf(1, 2, 3, 4, 5, 6, 7).asFlow()

//        = flow {
//    for (i in 1..10) {
//        emit(i)
//    }
//}

fun sendNewNumbers() = listOf(1,2,3,4,5,6,7,8,9).asFlow()

fun sendCancelNumbers() = flow {
    listOf(1,2,3,4,5,6,7,8,9).forEach {
        delay(400L)
        emit(it)
    }
}