package shared_state

import kotlinx.coroutines.*

/**
 * Thread Confinement
 *  All access to shared data is confined to a single thread.
 *  No parallel processing with this implementation.
 *
 *  Fine-Grained: Each individual increment switches context
 *      much slower
 *
 *  Coarse-Grained: The whole function is run on a single thread.
 *      No context switching; faster
 */

fun main() {
    runBlocking {
        // Fine-Grained, Slower Solution
        // Anything done with the counter var MUST go through the counterContext Val
        val fineCounterContext = newSingleThreadContext("FineCounterContext")
        var fineCounter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                // Switching to the counterContext
                withContext(fineCounterContext) { fineCounter++ }
            }
        }
        // This solution is MUCH slower (view the action ms)
        println("Fine-Grained Counter = $fineCounter")

        // Coarse-Grained, Faster Solution
        val coarseCounterContext = newSingleThreadContext("CoarseCounterContext")
        var coarseCounter = 0
        // Switching context
        withContext(coarseCounterContext) {
            // Function inside of the context
            massiveRun {
                coarseCounter++
            }
        }
        println("Coarse-Grain Counter = $coarseCounter")
     }
}