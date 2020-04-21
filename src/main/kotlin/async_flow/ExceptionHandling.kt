package async_flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    runBlocking {
        tryCatch()
        catch()
        onCompletion()
    }

}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
                .onEach { check(it != 2) }
                .collect { println(it) }
    } catch (e: Exception) {
        println("Caught Exception $e")
    }
}

suspend fun catch() {
    (1..3).asFlow()
            .onEach { check(it != 2) }
            .catch { e -> println("Caught Exception $e") }
            .collect { println(it) }
}

suspend fun onCompletion() {
    (1..3).asFlow()
            .onEach { check(it != 2) }
            .onCompletion { cause ->
                if(cause != null)
                    println("Flow Completed with $cause")
                else
                    println("Flow Completed successfully")
            }
            .catch { e -> println("Caught Exception $e") }
            .collect { println(it) }
}