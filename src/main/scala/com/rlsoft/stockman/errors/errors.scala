package com.rlsoft.stockman.errors

trait AppError

case object GenericError extends AppError
case object ResourceNotFound extends AppError
