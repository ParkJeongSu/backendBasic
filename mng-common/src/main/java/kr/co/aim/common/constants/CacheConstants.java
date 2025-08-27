package kr.co.aim.common.constants;

public final class CacheConstants {

    // private 생성자로 인스턴스화 방지
    private CacheConstants() {}

    public static final String USER_CACHE_NAME = "users";
    public static final int DEFAULT_TTL_SECONDS = 3600; // 1시간
}