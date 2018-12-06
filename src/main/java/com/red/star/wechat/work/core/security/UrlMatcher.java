package com.red.star.wechat.work.core.security;

public interface UrlMatcher {
    Object compile(String paramString);

    boolean pathMatchesUrl(Object paramObject, String paramString);
}
