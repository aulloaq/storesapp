package com.aulloaq.storesapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class SuspendHandlerUseCaseParams<in P, R : Any>(
    private val dispatcher: CoroutineContext = Dispatchers.IO
) {
    suspend operator fun invoke(params: P): HandlerResult<R> {
        return withContext(dispatcher) {
            try {
                execute(params)
            } catch (e: Exception) {
                HandlerResult.Failure(e)
            }
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P): HandlerResult<R>
}