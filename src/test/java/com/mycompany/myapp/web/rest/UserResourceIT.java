package com.mycompany.myapp.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mycompany.myapp.domain.UserModel;
import com.mycompany.myapp.repository.UserResourceRepository;
import com.mycompany.myapp.service.dto.UserOwnDTO;
import com.mycompany.myapp.service.impl.UserResourceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class UserResourceIT {

    @Mock
    private UserResourceRepository userResourceRepository;

    @InjectMocks
    private UserResourceServiceImpl userResourceService;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateUser() {
        UserOwnDTO vm = new UserOwnDTO();
        vm.setName("Jane");

        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("Jane");

        when(userResourceRepository.save(any())).thenReturn(Mono.just(user));

        Mono<UserModel> result = userResourceService.create(vm);

        StepVerifier.create(result)
            .assertNext(u -> {
                assertEquals("John", u.getName());
            })
            .verifyComplete();
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;

        UserOwnDTO vm = new UserOwnDTO();
        vm.setEmail("Ly Fanau");

        UserModel existingUser = new UserModel();
        existingUser.setId(1L);
        existingUser.setName("Old Name");

        UserModel updateUser = new UserModel();
        updateUser.setId(userId);
        updateUser.setName("Ly Fanau");

        // find existing user
        when(userResourceRepository.findById(userId)).thenReturn(Mono.just(existingUser));

        // update existing user
        when(userResourceRepository.save(any())).thenReturn(Mono.just(updateUser));

        Mono<UserModel> result = userResourceService.update(userId, vm);

        StepVerifier.create(result).expectNextMatches(user -> user.getName().equals("Ly Fanau")).verifyComplete();
    }

    @Test
    void deletedUser() {
        // declare for initialize
        Long userId = 2L;

        // mock test object
        UserModel user = new UserModel();
        user.setId(userId);
        user.setName("John");

        // find the user within repository
        when(userResourceRepository.findById(2L)).thenReturn(Mono.just(user));

        // deleted user
        when(userResourceRepository.delete(user)).thenReturn(Mono.empty());

        // calling service to delete the user
        Mono<Void> result = userResourceService.delete(userId);

        // verify the step
        StepVerifier.create(result).verifyComplete();
    }
}
