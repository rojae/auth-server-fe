package io.github.rojae.authsigninweb.common.enums;

public enum ApiCode {

    OK("A0000", "성공"),

    // API Level //
    INVALID_HEADER("A1000", "헤더에 데이터가 존재하지 않습니다"),
    INVALID_BODY("A1001", "바디 데이터가 존재하지 않습니다"),
    INVALID_QUERYSTRING("A1002", "쿼리스트링 데이터가 존재하지 않습니다"),
    INVALID_QUERYSTRING_HEADER("A1003", "쿼리스트링 혹은 헤더가 존재하지 않습니다"),

    INVALID_SERVICE_TOKEN("A1004", "유효하지 않는 서비스 토큰 요청입니다"),
    INVALID_KAKAO_TOKEN("A1005", "유효하지 않는 카카오 토큰 요청입니다"),

    // Database Level //
    DATABASE_TRANSACTION_FAIL("A2000", "데이터베이스 작업에 실패했습니다"),

    // Service Level //
    LOGIN_ACCOUNT_INVALID("A3000", "요청 데이터로 로그인에 실패했습니다."),
    SIGNUP_DUPLICATE("A3001", "이미 가입된 회원정보가 있습니다"),
    SIGNUP_FAILED("A3002", "회원가입에 실패하였습니다"),

    AUTH_ACCOUNT_INVALID("A3003", "인증 가능한 회원정보가 없습니다"),

    // PROFILE //
    NOTFOUND_PROFILE("A3004", "조회가능한 프로필이 없습니다"),

    // API //
    COREAPI_ERROR("ERR-COREAPI", "Core API에 오류가 발생했습니다"),
    OAUTH2API_ERROR("ERR-OAUTH2API", "OAuth2 API에 오류가 발생했습니다"),
    SOCIALAPI_ERROR("ERR-SOCIALAPI", "Social API에 오류가 발생했습니다"),

    // FRONT //
    CAPTCHA_FAILED("A8000", "로그인이 거부되었습니다. captcha 인증을 하지 않은 시도입니다."),
    INVALID_PLATFORM("A8001", "정의된 플랫폼 형식이 아닙니다"),

    // UNKNOWN //
    UNKNOWN("UNKNOWN", "알 수 없음"), ;

    private final String code;
    private final String reason;

    ApiCode(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public static ApiCode ofCode(String code){
        for(var e : ApiCode.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return UNKNOWN;
    }
}
