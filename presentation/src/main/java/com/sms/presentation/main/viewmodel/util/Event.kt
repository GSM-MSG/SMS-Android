package com.sms.presentation.main.viewmodel.util

sealed class Event<T>(
    val data: T? = null
) {

    class Loading<T> : Event<T>()

    /**
     * 성공
     */
    class Success<T>(data: T? = null) : Event<T>(data = data)

    /**
     * 400번 요청이 올바르지 않은 경우
     */
    class BadRequest<T> : Event<T>()

    /**
     * 401번 비인증 요청
     */
    class Unauthorized<T> : Event<T>()

    /**
     * 403번 권한이 없음
     */
    class ForBidden<T> : Event<T>()

    /**
     * 404 찾을 수 없는 경우
     */
    class NotFound<T> : Event<T>()

    /**
     * 406 맞는 규격이 없는 경우
     */
    class NotAcceptable<T> : Event<T>()

    /**
     * 408 요청이 너무 오래 걸리는 경우
     */
    class TimeOut<T> : Event<T>()

    /**
     * 409 권한이 없을 때
     */
    class Conflict<T> : Event<T>()

    /**
     * 50X 서버에러
     */
    class Server<T> : Event<T>()

    /**
     * 예상치 못한 에러
     */
    class UnKnown<T> : Event<T>()

}