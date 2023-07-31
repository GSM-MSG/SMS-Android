package com.msg.sms.domain.exception

/**
 * 요청이 올바르지 않은 경우
 * Http Status 가 400번일 때 사용
 * */
class BadRequestException(
    override val message: String?
) : RuntimeException()


/**
 * 비인증 되었을 경우 -> 인증을 해야함
 * Http Status 가 401번일 때 사용
 */
class UnauthorizedException(
    override val message: String?
) : RuntimeException()


/**
 * 권한이 없는 경우
 * Http Status 가 403번일 때 사용
 */
class ForBiddenException(
    override val message: String?
) : RuntimeException()


/**
 * 요청 받은 리소스를 찾을 수 없는 경우
 * Http Status 가 404번일 때 사용
 */
class NotFoundException(
    override val message: String?
) : RuntimeException()


/**
 * 에이전트가 정해준 규격에 맞는게 없을 경우
 * Http Status 가 406번일 때 사용
 */
class NotAcceptableException(
    override val message: String?
) : RuntimeException()

/**
 * 요청이 너무 오래 걸리는 경우
 * Http Status가 408번 일 때 사용
 */
class TimeOutException(
    override val message: String?
) : RuntimeException()


/**
 * 권한이 없을 경우
 * Http Status 가 409일 때 사용
 * */
class ConflictException(
    override val message: String?
) : RuntimeException()


/**
 * 서버에러가 발생하는 경우
 * Http Status 가 50X일 때 사용
 */
class ServerException(
    override val message: String?
) : RuntimeException()


/**
 * 예상하지 못한 에러가 발생하는 경우
 */
class OtherHttpException(
    val code: Int,
    override val message: String?
) : RuntimeException()


class UnKnownException(
    override val message: String?
) : RuntimeException()


