package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Fan-In
 *  Opposing principle of Fan-Out:
 *  Multiple coroutines can send values to the same channel
 */

fun main() {
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "message1", 200L) }
        launch { sendString(channel, "message2", 500L) }
        repeat(6) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }
}

suspend fun sendString(
        channel: SendChannel<String>,
        message: String,
        time: Long
) {
    while (true) {
        delay(time)
        channel.send(message)
    }
}