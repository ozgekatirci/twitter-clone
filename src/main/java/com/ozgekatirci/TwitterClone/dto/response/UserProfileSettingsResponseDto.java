package com.ozgekatirci.TwitterClone.dto.response;

import lombok.Data;

@Data
public class UserProfileSettingsResponseDto {
    private String username;
    private String email;
    private String nameAndSurname;
    private String bio;
    private String location;
    private String website;
    private String birthDate;
    private String phoneNumber;

}
