package com.ozgekatirci.TwitterClone.controller;


import com.ozgekatirci.TwitterClone.business.abstracts.UserSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/settings")
public class UserSettingsController {
    private final UserSettingsService userSettingsService;

    @PutMapping("/update/Username")
    public ResponseEntity<String> updateUsername(@RequestParam Long id, @RequestParam String username){
        return ResponseEntity.ok(userSettingsService.updateUsername(id,username));
    }
    @PutMapping("/update/Password")
    public ResponseEntity<String> updatePassword(@RequestParam Long id, @RequestParam String password){
        return ResponseEntity.ok(userSettingsService.updatePassword(id,password));
    }
    @PutMapping("/update/Email")
    public ResponseEntity<String> updateEmail(@RequestParam Long id, @RequestParam String email){
        return ResponseEntity.ok(userSettingsService.updateEmail(id,email));
    }
    @PutMapping("/update/Bio")
    public ResponseEntity<String> updateBio(@RequestParam Long id, @RequestParam String bio){
        return ResponseEntity.ok(userSettingsService.updateBio(id,bio));
    }
    @PutMapping("/update/Location")
    public ResponseEntity<String> updateLocation(@RequestParam Long id, @RequestParam String location){
        return ResponseEntity.ok(userSettingsService.updateLocation(id,location));
    }
    @PutMapping("/update/Website")
    public ResponseEntity<String> updateWebsite(@RequestParam Long id, @RequestParam String website){
        return ResponseEntity.ok(userSettingsService.updateWebsite(id,website));
    }
    @PutMapping("/update/Birthday")
    public ResponseEntity<String> updateBirthday(@RequestParam Long id, @RequestParam String birthday){
        return ResponseEntity.ok(userSettingsService.updateBirthday(id,birthday));
    }
    @PutMapping("/update/PhoneNumber")
    public ResponseEntity<String> updatePhoneNumber(@RequestParam Long id, @RequestParam String phoneNumber){
        return ResponseEntity.ok(userSettingsService.updatePhoneNumber(id,phoneNumber));
    }
    @PutMapping("/update/Gender")
    public ResponseEntity<String> updateGender(@RequestParam Long id, @RequestParam String gender){
        return ResponseEntity.ok(userSettingsService.updateGender(id,gender));
    }

    @PutMapping("/update/Language")
    public ResponseEntity<String> updateLanguage(@RequestParam Long id, @RequestParam String language){
        return ResponseEntity.ok(userSettingsService.updateLanguage(id,language));
    }

}
