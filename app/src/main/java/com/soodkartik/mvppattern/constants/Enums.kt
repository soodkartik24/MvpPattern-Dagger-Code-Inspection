package com.soodkartik.mvppattern.constants

object Enums {
    enum class ErrorType {
        VALIDATION, API, OTHER
    }

    enum class RequestType {
        Post, Get, Put, Delete
    }

    enum class SharePreferencesEnum {
        StringType, BooleanType, IntType, FloatType, LongType, ModelType
    }

    enum class Gender(val type: Int) {
        None(0), Male(1), Female(2), Transgender(3)
    }

    enum class UserType(val type: Int) {
        Admin(0), User(1)
    }
}