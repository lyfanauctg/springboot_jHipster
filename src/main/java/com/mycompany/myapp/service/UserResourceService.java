package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.UserModel;
import com.mycompany.myapp.service.dto.UserOwnDTO;
import com.mycompany.myapp.web.rest.UserResource;
import com.mycompany.myapp.web.rest.vm.user.UserVM;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserResourceService {
    Flux<UserModel> getAllUser();

    Mono<UserModel> create(UserOwnDTO req);

    Mono<UserModel> getUserById(Long id);

    Mono<Void> delete(Long id);

    Mono<UserModel> update(Long id, UserOwnDTO req);
}
