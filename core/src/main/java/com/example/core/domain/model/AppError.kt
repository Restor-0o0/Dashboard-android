package com.example.core.domain.model

import androidx.annotation.StringRes
import com.example.core.R


sealed class AppError(@StringRes val messageRes: Int? = null, val code: Int? = null, val message: String? = null) {
    object BadRequest: AppError(R.string.bad_request,400)
    object NetworkError : AppError(R.string.error_network)
    object Unauthorized : AppError(R.string.error_unauthorized, 401)
    object Forbidden: AppError(R.string.error_forbidden,403)
    object NotFound : AppError(R.string.error_not_found, 404)
    object Timeout: AppError(R.string.error_timeout,408)
    object ServerError : AppError(R.string.error_server, 500)
    data class OtherApiError(val customCode: Int, val customMessage: String) :
        AppError(
            message = customMessage,
            code = customCode
        )

    // Для обычных (не сетевых) ошибок код = null
    object TokenNotFound: AppError(R.string.error_token_not_found)
    object ValidationError : AppError(R.string.error_validation)

    // Кастомная ошибка (например, если сервер вернул неизвестный код)
    data class CustomError(val customCode: Int, @StringRes val customMessageRes: Int) :
        AppError(customMessageRes, customCode)


}