package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Ticker Channels
 *  Periodically produces a Unit after a given delay
 *  Has an optimal initial delay
 */

fun main() {
    runBlocking {
        val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0)
        launch {
            val startTime = System.currentTimeMillis()
            tickerChannel.consumeEach {
                val delta = System.currentTimeMillis() - startTime
                println("Received tick after $delta")
            }
        }
        delay(1000L)
        println("Done!!")
        tickerChannel.cancel()
    }

}