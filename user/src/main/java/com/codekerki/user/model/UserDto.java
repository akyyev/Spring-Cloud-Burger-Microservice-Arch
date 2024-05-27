package com.codekerki.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private String name;
    private boolean active;
}
