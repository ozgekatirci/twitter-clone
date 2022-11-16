package com.ozgekatirci.TwitterClone.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String bio;
    private boolean isProtectedProfile;


}
