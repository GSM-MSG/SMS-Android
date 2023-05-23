package com.sms.presentation.main.viewmodel.util

sealed class Event (
) {

    object Loading: Event()

    /**
     * 성공
     */
    object Success: Event()

    /**
     * 400번 요청이 올바르지 않은 경우
     */
    object BadRequest: Event()

    /**
     * 401번 비인증 요청
     */
    object Unauthorized: Event()

    /**
     * 403번 권한이 없음
     */
    object ForBidden: Event()

    /**
     * 404 찾을 수 없는 경우
     */
    object NotFound: Event()

    /**
     * 406 맞는 규격이 없는 경우
     */
    object  NotAcceptable: Event()

    /**
     * 408 요청이 너무 오래 걸리는 경우
     */
    object TimeOut: Event()

    /**
     * 409 권한이 없을 때
     */
    object Conflict: Event()

    /**
     * 50X 서버에러
     */
    object Server: Event()

    /**
     * 예상치 못한 에러
     */
    object UnKnown: Event()

}