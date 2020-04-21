package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Fan-Out
 * If multiple coroutines receive from the same channel, values
 * (work) are distributed among them.
 */

fun main() {
    runBlocking {
        val producer = produceNumbersTwo()
        repeat(5) {
            launchProcessor(it, producer)
            delay(1500L)
            producer.cancel()
        }
    }
}

fun CoroutineScope.produceNumbersTwo() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(
        id: Int, channel: ReceiveChannel<Int>
) = launch {
    for (msg in channel)
        println("Processor #$id received $msg")
}