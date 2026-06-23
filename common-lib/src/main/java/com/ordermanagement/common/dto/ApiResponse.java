package com.ordermanagement.common.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
     boolean success,
     String message,
    T data,
    Instant timestamp
)
{
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(true,message,data,Instant.now());
    }
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<T>(true,"Request Completed Successfully",data,Instant.now());
    }
    public static <T>ApiResponse<T> failure(String message){
        return new ApiResponse<T>(false,message,null,Instant.now());
    }
}