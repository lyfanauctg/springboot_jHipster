package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.UserModel;
import com.mycompany.myapp.repository.UserResourceRepository;
import com.mycompany.myapp.service.UserResourceService;
import com.mycompany.myapp.service.dto.UserOwnDTO;
import com.mycompany.myapp.web.rest.UserResource;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.errors.ResourceNotFoundException;
import com.mycompany.myapp.web.rest.vm.user.UserVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserResourceServiceImpl implements UserResourceService {

    private final UserResourceRepository userResourceRepository;

    @Override
    public Flux<UserModel> getAllUser() {
        return userResourceRepository.findAll();
    }

    @Override
    public Mono<UserModel> create(UserOwnDTO req) {
        UserModel user = new UserModel();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        return userResourceRepository.save(user);
    }

    @Override
    public Mono<UserModel> getUserById(Long id) {
        return userResourceRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return userResourceRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
            .flatMap(userResourceRepository::delete);
    }

    @Override
    public Mono<UserModel> update(Long id, UserOwnDTO req) {
        return userResourceRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
            .flatMap(user -> {
                user.setName(req.getName());
                user.setEmail(req.getEmail());
                return userResourceRepository.save(user);
            });
    }
}
