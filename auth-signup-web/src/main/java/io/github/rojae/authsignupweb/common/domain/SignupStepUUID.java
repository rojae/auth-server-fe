package io.github.rojae.authsignupweb.common.domain;

import io.github.rojae.authsignupweb.dto.SignupRedisData;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.servlet.http.Cookie;
import java.io.Serializable;

@RedisHash(value = "SIGNUP_STEP_UUID")
public class SignupStepUUID implements Serializable {

    public SignupStepUUID() {
    }

    public SignupStepUUID(Cookie cookie) {
        this.id = SignupStepUUID.idFormat(cookie.getName(), cookie.getValue());
    }

    public SignupStepUUID(Cookie cookie, SignupRedisData data) {
        this.id = SignupStepUUID.idFormat(cookie.getName(), cookie.getValue());
        this.data = data;
    }

    @Id
    private String id;
    private SignupRedisData data;

    public static String idFormat(String name, String value) {
        return String.format("%s:%s", name, value);
    }

    @TimeToLive
    public long getTimeToLive() {
        return 30 * 60;         // 30m
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SignupRedisData getData() {
        return data;
    }

    public void setData(SignupRedisData data) {
        this.data = data;
    }
}
