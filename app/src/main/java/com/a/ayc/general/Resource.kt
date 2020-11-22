package com.a.ayc.general

import androidx.lifecycle.MutableLiveData
import com.a.ayc.general.Resource.*

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Canceled : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            Loading -> "Loading"
            Canceled -> "Canceled"
        }
    }
}

inline fun <T> Resource<T>.withResult(
    onLoading: (Boolean) -> Unit,
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    when (this) {
        Loading -> onLoading(true)
        Canceled -> onLoading(false)
        is Error -> {
            onLoading(false)
            onFailure(error)
        }
        is Success -> {
            onLoading(false)
            onSuccess(data)
        }
    }
}

/**
 * [Success.data] if [Resource] is of type [Success]
 */
fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

fun <T> Resource<T>.isLoading() = this is Loading
fun <T> Resource<T>.isSuccess() = this is Success

inline fun <R> Resource<R>.onSuccess(action: (R) -> Unit): Resource<R> {
    if (this is Success) {
        action(data)
    }
    return this
}


/**
 * `true` if [Resource] is of type [Success] & holds non-null [Success.data].
 */
val Resource<*>.succeeded
    get() = this is Success && data != null


val <T> Resource<T>.data: T?
    get() = (this as? Success)?.data

/**
 * Updates value of [liveData] if [Resource] is of type [Success]
 */
inline fun <reified T> Resource<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is Success) {
        liveData.value = data
    }
}

inline fun <R> Resource<R>.onLoading(action: () -> Unit): Resource<R> {
    if (this is Loading) {
        action()
    }
    return this
}



inline fun <T> Resource<T>.onError(onFailure: (Throwable) -> Unit) {
    if (this is Error) onFailure(error)
}

/*
inline fun <R> Result<R>.onRetry(action: () -> Unit) {
    if ((this is HttpException && AppConstants.ErrorList.CONNECTION.contains(code())
                ||
                this is Error && this.error is IOException)
    ) {
        action()
    }
}*/
