package com.ozgekatirci.TwitterClone.controller;

import com.ozgekatirci.TwitterClone.business.abstracts.UserService;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserProfileSettingsResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")

public class UserContoller {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById")
    public UserProfileSettingsResponseDto getUserById(Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/getLikedTweets")
    public List<TweetResponseDto> getLikedTweets(Long id) {
        return userService.getUserLikedTweets(id);
    }
    @GetMapping("/getRetweetedTweets")
    public List<TweetResponseDto> getRetweetedTweets(Long id) {
        return null;
    }

    @GetMapping("/getTweets")
    public List<TweetResponseDto> getTweets(Long id) {
        return userService.getTweetsById(id);
    }

    @GetMapping("/getFollowers")
    public List<UserResponseDto> getFollowers(Long id) {
        return userService.getFollowers(id);
    }

    @GetMapping("/getFollowings")
    public List<UserResponseDto> getFollowings(Long id) {
        return userService.getFollowings(id);
    }

   @PostMapping("/follow")
    public void follow(Long followerId, Long followingId) {
        userService.followUser(followerId, followingId);
    }

    @PostMapping("/unfollow")
    public void unfollow(Long followerId, Long followingId) {
        userService.unfollowUser(followerId, followingId);
    }


    @PostMapping("/delete")
    public void delete(Long id) {
        userService.deleteAccount(id);
    }

}
