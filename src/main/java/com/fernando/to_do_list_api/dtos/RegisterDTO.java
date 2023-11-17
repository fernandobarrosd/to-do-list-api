package com.fernando.to_do_list_api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
    @NotEmpty
    String username,

    @NotEmpty
    @Size(max = 20)
    String password
) {}