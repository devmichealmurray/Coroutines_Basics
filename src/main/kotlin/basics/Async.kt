package basics

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        val firstDeferred = async { getFirstValue() }
        val secondDeferred = async { getSecondValue() }

        println("Processing....")
        delay(500L)
        println("Waiting for values")

        val firstValue = firstDeferred.await()
        val secondValue = secondDeferred.await()

        println("Our Total: ${firstValue + secondValue}")

    }
}

suspend fun getFirstValue(): Int {
    delay(1000L)
    val value = Random.nextInt(1000)
    println("Returning First Value: $value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(2000L)
    val value = Random.nextInt(2000)
    println("Returning Second Vale: $value")
    return value
}