package com.fernando.to_do_list_api.dtos;

public record ErrorDTO(
    String message,
    String path,
    Integer statusCode
) {}