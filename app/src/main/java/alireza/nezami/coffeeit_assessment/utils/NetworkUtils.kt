package alireza.nezami.coffeeit_assessment.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class NetworkState<out T> {
    object LOADING : NetworkState<Nothing>()
    data class SUCCESS<T>(val value: T) : NetworkState<T>()
    data class ERROR(val message: String? = null) : NetworkState<Nothing>()
}

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val code: Int? = null, val error: String? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object NetworkError : Result<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.errorBody
                    Result.Error(code, errorResponse)
                }
                else -> Result.Error(null, throwable.message)
            }
        }
    }
}

private val HttpException.errorBody: String?
    get() = try {
        this.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        null
    }
