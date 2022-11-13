package com.ozgekatirci.TwitterClone.controller;


import com.ozgekatirci.TwitterClone.business.abstracts.TweetService;
import com.ozgekatirci.TwitterClone.dto.request.TweetRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tweet")
public class TweetController {
    private final TweetService tweetService;

    @GetMapping("/all")
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/getById")
    public TweetResponseDto getTweetById(Long id) {
        return tweetService.getTweetByTweetId(id);
    }

    @GetMapping("/getByUserId")
    public List<TweetResponseDto> getTweetByUserId(Long id) {
        return tweetService.getTweetsById(id);
    }

    @GetMapping("/getLikedUser")
    public List<UserResponseDto> getLikedTweets(Long tweetId) {
        return tweetService.getLikedUsersByTweetId(tweetId);
    }

    @GetMapping("/getRetweetedUser")
    public List<UserResponseDto> getRetweetedTweets(Long tweetId) {
        return tweetService.getRetweetedUsersByTweetId(tweetId);

    }

    @PostMapping("/like")
    public void likeTweet(Long tweetId, Long userId) {
        tweetService.likeTweet(tweetId, userId);
    }

    @PostMapping("/retweet")
    public void retweet(Long tweetId, Long userId) {
        tweetService.createRetweet(tweetId, userId);
    }

    @PostMapping("/create")
    public void createTweet(Long userId, TweetRequestDto tweetRequestDto) {
        tweetService.createTweet(userId, tweetRequestDto);
    }

    @PostMapping("/delete")
    public void deleteTweet(Long tweetId) {
        tweetService.deleteTweet(tweetId);
    }

    @PutMapping("/update")
public void updateTweet(Long tweetId, TweetRequestDto tweetRequestDto) {
        tweetService.updateTweet(tweetId, tweetRequestDto);
    }

}
