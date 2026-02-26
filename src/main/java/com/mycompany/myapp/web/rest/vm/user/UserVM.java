package com.mycompany.myapp.web.rest.vm.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVM {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String email;
}
