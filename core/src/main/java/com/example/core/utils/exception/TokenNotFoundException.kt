package com.example.core.utils.exception

class TokenNotFoundException(
    message: String = "Token not found, need authorization"
): Exception(message)