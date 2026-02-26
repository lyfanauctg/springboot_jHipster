package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.UserModel;
import com.mycompany.myapp.service.UserResourceService;
import com.mycompany.myapp.web.rest.vm.user.UserVM;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserResourceController {

    private final UserResourceService userResourceService;

    @GetMapping("/all")
    public Flux<UserModel> getAllUser() {
        return userResourceService.getAllUser();
    }

    @PostMapping("/create")
    public Mono<UserModel> createUser(@Valid @RequestBody UserVM req) {
        return userResourceService.create(req);
    }

    @GetMapping("/{id}")
    public Mono<UserModel> getUserById(@Valid @Positive @PathVariable Long id) {
        return userResourceService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return userResourceService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<UserModel> updateById(@Valid @PathVariable Long id, @RequestBody UserVM req) {
        return userResourceService.update(id, req);
    }
}
