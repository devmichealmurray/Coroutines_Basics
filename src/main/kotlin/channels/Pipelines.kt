package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * PIPELINES
 *  A pipeline is a development pattern where one channel output
 *  is given as an input to another channel.
 *
 *  One coroutine is producing a (potentially infinite) set of
 *  values and one or more coroutines are consuming and transforming
 *  those values
 */

fun main() {
    runBlocking {
        val numbers = produceNumbers()
        val squares = square(numbers)
        for (i in 1..15) println(squares.receive())
        println("Done!")
        coroutineContext.cancelChildren()
    }
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true)
        send(x++)
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce<Int> {
    for (x in numbers)
        send(x * x)
}