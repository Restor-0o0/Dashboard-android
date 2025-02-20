package com.example.dashboard.common.exception

class TokenNotFoundException(
    message: String = "Token not found, need authorization"
): Exception(message)