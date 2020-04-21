package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Buffered Channels
 *  A buffered channel has a limited capacity
 *  When the capacity is reached, the sender is paused.
 *  When capacity becomes available, new values can be
 *  sent.
 */

fun main() {

    runBlocking {
        val channel = Channel<Int>(4)
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }
        repeat(3) {
            delay(1000L)
            println("Received ${channel.receive()}")
        }
    sender.cancel()
    }
}

