package com.ozgekatirci.TwitterClone.controller;


import com.ozgekatirci.TwitterClone.business.abstracts.TweetService;
import com.ozgekatirci.TwitterClone.dto.request.TweetRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    public TweetResponseDto getTweetById(@RequestParam Long id) {
        return tweetService.getTweetByTweetId(id);
    }

    @GetMapping("/getByUserId")
    public List<TweetResponseDto> getTweetByUserId(@RequestParam Long id) {
        return tweetService.getTweetsById(id);
    }

    @GetMapping("/getLikedUser")
    public List<UserResponseDto> getLikedTweets(@RequestParam Long tweetId) {
        return tweetService.getLikedUsersByTweetId(tweetId);
    }

    @GetMapping("/getRetweetedUsers")
    public List<UserResponseDto> getRetweetedTweets(@RequestParam Long tweetId) {
        return tweetService.getRetweetedUsersByTweetId(tweetId);

    }
    @GetMapping("/like")
    public Map<String, Object> likeTweet(@RequestParam Long tweetId, @RequestParam  Long userId) {
        return tweetService.likeTweet(tweetId, userId);
    }

    @GetMapping("/retweet")
    public Map<String, Object> retweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        return tweetService.createRetweet(tweetId, userId);
    }

    @PostMapping("/create")
    public TweetResponseDto createTweet(@RequestParam Long userId, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(userId, tweetRequestDto);
    }
    @PostMapping("/delete")
    public String deleteTweet(@RequestParam Long tweetId) {
        return tweetService.deleteTweet(tweetId);
    }

    @PutMapping("/update")
public TweetResponseDto updateTweet(@RequestParam Long tweetId, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.updateTweet(tweetId, tweetRequestDto);
    }


}
