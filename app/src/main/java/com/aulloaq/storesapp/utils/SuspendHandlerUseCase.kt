package com.aulloaq.storesapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class SuspendHandlerUseCase<out R : Any>(
    private val dispatcher: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(): HandlerResult<R> {
        return withContext(dispatcher) {
            try {
                execute()
            } catch (e: Exception) {
                HandlerResult.Failure(e)
            }
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): HandlerResult<R>
}