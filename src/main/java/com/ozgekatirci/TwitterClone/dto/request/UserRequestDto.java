package com.ozgekatirci.TwitterClone.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String nameAndSurname;
    private String bio;
    private String location;
    private String website;
    private String birthDate;
}
