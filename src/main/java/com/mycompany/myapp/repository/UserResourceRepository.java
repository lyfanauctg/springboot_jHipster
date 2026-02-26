package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.UserModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResourceRepository extends ReactiveCrudRepository<UserModel, Long> {}
