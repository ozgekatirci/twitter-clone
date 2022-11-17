package com.ozgekatirci.TwitterClone.business.abstracts;



public interface UserSettingsService {
    public String updateUsername(Long id,String username);

    public String updateLanguage(Long id, String language);

    public String updateGender(Long id, String gender);

    public String updatePhoneNumber(Long id, String phoneNumber);

    public String updateBirthday(Long id, String birthday);

    public String updateWebsite(Long id, String website);

    public String updateLocation(Long id, String location);

    public String updateBio(Long id, String bio);

    public String updateEmail(Long id, String email);

    public String updatePassword(Long id, String password);
}
