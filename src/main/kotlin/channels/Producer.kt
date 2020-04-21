package channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = produce {
            for (x in 1..5)
                send(x * x)
        }

        for (y in channel)
            println(y)

        // calling extension function
        for (i in produceSquares())
            println(i)

        //simplified call on extension function
        produceSquares().consumeEach { println(it) }
    }
}

/**
 *  Exentsion function on CoroutineScope
 */

fun CoroutineScope.produceSquares() = produce {
    for (x in 1..5)
        send(x * x)
}