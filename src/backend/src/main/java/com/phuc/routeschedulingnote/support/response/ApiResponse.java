package com.phuc.routeschedulingnote.support.response;

import com.phuc.routeschedulingnote.support.error.ErrorMessage;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private ResultType result;
    private T data;
    private ErrorMessage error;

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(ErrorType errorType) {
        ErrorMessage errorMessage = new ErrorMessage(errorType);
        return new ApiResponse<>(ResultType.ERROR, null, errorMessage);
    }
}
