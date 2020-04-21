package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5)
                channel.send(x * x)
            channel.close()
        }

        for (i in channel) {
            println(i)
        }
    }
}