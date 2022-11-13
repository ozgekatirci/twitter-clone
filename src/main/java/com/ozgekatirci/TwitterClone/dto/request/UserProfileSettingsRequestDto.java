package com.ozgekatirci.TwitterClone.dto.request;

import lombok.Data;

@Data
public class UserProfileSettingsRequestDto {
    private String username;
    private String email;
    private String nameAndSurname;
    private String bio;
    private String location;
    private String website;
    private String birthDate;
    private String phoneNumber;

}
