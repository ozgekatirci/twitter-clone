package com.ozgekatirci.TwitterClone.controller;

import com.ozgekatirci.TwitterClone.business.abstracts.UserService;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserProfileSettingsResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
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
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/getById")
    public UserProfileSettingsResponseDto getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/getLikedTweets")
    public List<TweetResponseDto> getLikedTweets(@RequestParam Long id) {
        return userService.getUserLikedTweets(id);
    }
    @GetMapping("/getRetweetedTweets")
    public List<TweetResponseDto> getRetweetedTweets(@RequestParam Long id) {
        return null;
    }

    @GetMapping("/getTweets")
    public List<TweetResponseDto> getTweets(@RequestParam Long id) {
        return userService.getTweetsById(id);
    }

    @GetMapping("/getFollowers")
    public List<UserResponseDto> getFollowers(@RequestParam Long id) {
        return userService.getFollowers(id);
    }

    @GetMapping("/getFollowings")
    public List<UserResponseDto> getFollowings(@RequestParam Long id) {
        return userService.getFollowings(id);
    }

   @PostMapping("/follow")
    public void follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        userService.followUser(followerId, followingId);
    }

    @PostMapping("/unfollow")
    public void unfollow(@RequestParam  Long followerId, @RequestParam Long followingId) {
        userService.unfollowUser(followerId, followingId);
    }


    @PostMapping("/delete")
    public void delete(@RequestParam Long id) {
        userService.deleteAccount(id);
    }

}
