package com.dev.demo.DTO.ApiResponse;

public record ApiResponse<T>(String message, T data) {
}
