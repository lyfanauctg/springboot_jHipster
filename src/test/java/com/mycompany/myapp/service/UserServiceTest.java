package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.UserModel;
import com.mycompany.myapp.repository.UserResourceRepository;
import com.mycompany.myapp.service.impl.UserResourceServiceImpl;
import com.mycompany.myapp.web.rest.vm.user.UserVM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserResourceRepository userResourceRepository;

    @InjectMocks
    private UserResourceServiceImpl userService;

    @Test
    void updateUser_success() {
        Long id = 1L;
        UserModel existing = new UserModel();
        existing.setId(id);
        existing.setName("Old");
        existing.setEmail("old@mail.com");

        UserVM req = new UserVM();
        req.setName("New");
        req.setEmail("new@mail.com");

        Mockito.when(userResourceRepository.save(Mockito.<UserModel>any())).thenReturn(Mono.just(existing));

        Mockito.when(userResourceRepository.findById(id)).thenReturn(Mono.just(existing));

        Mono<UserModel> result = userService.update(id, req);

        StepVerifier.create(result).expectNextCount(1).verifyComplete();
    }
}
