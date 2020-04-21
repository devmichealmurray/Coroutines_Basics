package async_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                    .buffer()
                    .collect {
                        delay(300L)
                        println(it)
                    }
        }
        println("Collected in $time ms")
    }
}

fun generate(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}

