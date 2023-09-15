package pl.gornicki.postservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String username;
    private String email;
}
