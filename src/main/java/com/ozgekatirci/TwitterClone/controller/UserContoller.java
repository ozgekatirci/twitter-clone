package com.ozgekatirci.TwitterClone.controller;

import com.ozgekatirci.TwitterClone.business.abstracts.UserService;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserProfileSettingsResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")

public class UserContoller {

    private final UserService userService;

    @GetMapping("/save")
    public void saveUser(UserResponseDto userResponseDto) {
        userService.saveUser(userResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/getById")
    public  ResponseEntity<UserProfileSettingsResponseDto>  getUserById(@RequestParam Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/getLikedTweets")
    public ResponseEntity<List<TweetResponseDto>> getLikedTweets(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUserLikedTweets(id));
    }
    @GetMapping("/getRetweetedTweets")
    public ResponseEntity<List<TweetResponseDto>> getRetweetedTweets(@RequestParam Long id) {

        return ResponseEntity.ok(userService.getUserRetweetedTweets(id));
    }

    @GetMapping("/getTweets")
    public ResponseEntity<List<TweetResponseDto>> getTweets(@RequestParam Long id) {
            return ResponseEntity.ok(userService.getTweetsById(id));
    }

    @GetMapping("/getFollowers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getFollowers(id));
    }

    @GetMapping("/getFollowings")
    public ResponseEntity<List<UserResponseDto>>getFollowings(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getFollowings(id));
    }

   @GetMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        return ResponseEntity.ok(userService.followUser(followerId, followingId));
    }

    @GetMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam  Long followerId, @RequestParam Long followingId) {
        return ResponseEntity.ok(userService.unfollowUser(followerId, followingId));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {

        userService.deleteAccount(id);
    }

}
