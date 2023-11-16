package com.fernando.to_do_list_api.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import com.fernando.to_do_list_api.models.AuthUser;

public interface AuthUserRepository extends CrudRepository<AuthUser, Long>{
    Optional<AuthUser> findByUsername(String username);
}