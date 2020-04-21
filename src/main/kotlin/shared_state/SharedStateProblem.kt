package shared_state

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Shared State Problem
 *  Multiple coroutines can update a shared state variable concurrently.
 *
 *  Some updates may be lost; if multiple coroutines are updating the
 *  same variable at the same times -- unexpected outcomes may occur.
 */

fun main() {
    runBlocking {
        var counter = 0 // should return at 100_000
        withContext(Dispatchers.Default) {
            massiveRun { counter++ }
        }
        println("Counter = $counter") // should return at 100_000, but will not
    }

}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}
