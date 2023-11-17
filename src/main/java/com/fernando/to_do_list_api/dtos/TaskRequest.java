package com.fernando.to_do_list_api.dtos;

public record TaskRequest(
    String name,
    String description
) {}