package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"success", "data"})
public class BaseApiResponse<T> {
    private final boolean success;
    private final T data;

    private BaseApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> BaseApiResponse<T> ok(T data) {
        return new BaseApiResponse<>(true, data);
    }

    public static <T> BaseApiResponse<T> error(T data) {
        return new BaseApiResponse<>(false, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}
