package com.ll.medium.global.rsData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private final T data;
    private final int statusCode;

    public static <T> RsData<T> of(String resultCode, String resultMessage, T data) {
        int statusCode = Integer.parseInt(resultCode);

        return new RsData<>(resultCode, resultMessage, data, statusCode);
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 400;
    }

    public boolean isFail() {
        return !isSuccess();
    }
}
