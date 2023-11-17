package com.fernando.to_do_list_api.dtos;

public record UpdateTaskDTO(
    String name,
    String description,
    Boolean finish
) {}