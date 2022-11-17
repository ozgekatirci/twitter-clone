package com.ozgekatirci.TwitterClone.business.concretes;

import com.ozgekatirci.TwitterClone.business.abstracts.UserSettingsService;
import com.ozgekatirci.TwitterClone.model.User;
import com.ozgekatirci.TwitterClone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingsManager implements UserSettingsService {
    private final UserRepository userRepository;

    public String updateUsername(Long id,String username){
     User user = userRepository.findById(id).get();
        user.setUsername(username);
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public String updateLanguage(Long id, String language) {
        User user = userRepository.findById(id).get();
        user.setLanguage(language);
        userRepository.save(user);
        return user.getLanguage();
    }

    @Override
    public String updateGender(Long id, String gender) {
        User user = userRepository.findById(id).get();
        user.setGender(gender);
        userRepository.save(user);
        return user.getGender();
    }

    @Override
    public String updatePhoneNumber(Long id, String phoneNumber) {
        User user = userRepository.findById(id).get();
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
        return user.getPhoneNumber();
    }

    @Override
    public  String updateBirthday(Long id, String birthday) {
        User user = userRepository.findById(id).get();
        user.setBirthdate(birthday);
        userRepository.save(user);
        return user.getBirthdate();
    }

    @Override
    public String updateWebsite(Long id, String website) {
        User user = userRepository.findById(id).get();
        user.setWebsite(website);
        userRepository.save(user);
        return user.getWebsite();
    }

    @Override
    public String updateLocation(Long id, String location) {
        User user = userRepository.findById(id).get();
        user.setLocation(location);
        userRepository.save(user);
        return user.getLocation();
    }

    @Override
    public String updateBio(Long id, String bio) {
        User user = userRepository.findById(id).get();
        user.setBio(bio);
        userRepository.save(user);
        return user.getBio();
    }

    @Override
    public String updateEmail(Long id, String email) {
        User user = userRepository.findById(id).get();
        user.setEmail(email);
        userRepository.save(user);
        return user.getEmail();
    }

    @Override
    public String updatePassword(Long id, String password) {
        User user = userRepository.findById(id).get();
        user.setPassword(password);
        userRepository.save(user);
        return user.getPassword();
    }
}
