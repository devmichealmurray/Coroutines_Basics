package shared_state

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

/**
 * Atomic Variables
 *  The easiest solution to the Shared State Problem is to use an
 *  Atomic Variable for the counter.
 *
 *  An Atomic Integer is a value that can only be updated by one thread
 *  at a time.
 *
 *  Works well for primitive data types and collections
 *
 *  Difficult for complex data types with no thread-safe implementations
 */
fun main() {
    runBlocking {
        var counter = AtomicInteger() // can only be updated by one thread at a time
        withContext(Dispatchers.Default) {
            newMassiveRun { counter.incrementAndGet() }
        }
        println("Counter = $counter")
    }

}

suspend fun newMassiveRun(action: suspend () -> Unit) {
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
