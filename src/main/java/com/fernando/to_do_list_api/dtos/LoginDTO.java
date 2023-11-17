package com.fernando.to_do_list_api.dtos;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
    @NotEmpty
    String username,

    @NotEmpty
    @Length(min = 1, max = 20)
    String password
) {}