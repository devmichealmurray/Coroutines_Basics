package basics

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException

fun main() {

    runBlocking {

        val myHandler = CoroutineExceptionHandler {
            coroutineContext, throwable ->
            println("Exception Handled: ${throwable.localizedMessage}")
        }

        val job = GlobalScope.launch(myHandler) {
            println("Throwing Exception from job")
            throw IndexOutOfBoundsException("Exception in coroutine")
        }
        job.join()

        val deferred = GlobalScope.async {
            println("Throwing Exception from Async")
            throw ArithmeticException("Exception from Async")
        }

        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println("Caught Arithmetic Exception ${e.localizedMessage}")
        }
    }

}