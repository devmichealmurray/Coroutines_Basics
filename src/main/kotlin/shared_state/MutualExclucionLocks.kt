package shared_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Mutual Exclusion Locks
 *  Might be a good compromise between performance and parallel processing.
 *
 *  A mutual exclusion lock (mutex) locks access to a sensitive part of code
 *  with a lock & unlock system.
 *  A coroutine can lock and unlock the mutex preventing the variable from being
 *  updated simultaneously.
 *
 *  withLock {...} method provides both lock and unlock functionality
 */

fun main() {

    runBlocking {
        val mutex = Mutex()
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock { counter++ }
            }
        }
        println("Counter = $counter")
    }
}