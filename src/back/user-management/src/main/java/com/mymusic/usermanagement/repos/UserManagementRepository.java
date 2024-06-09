package com.mymusic.usermanagement.repos;

import com.mymusic.usermanagement.entities.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagementRepository extends CrudRepository<User, Long> {

    Optional<User> findById(@NonNull Long id);
    Optional<User> findByUsername(@NonNull String username);
    Optional<User> findByEmail(@NonNull String email);
    boolean existsById(@NonNull Long id);
    boolean existsByUsername(@NonNull String username);
    boolean existsByEmail(@NonNull String email);
}
