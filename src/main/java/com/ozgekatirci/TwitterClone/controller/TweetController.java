package com.ozgekatirci.TwitterClone.controller;


import com.ozgekatirci.TwitterClone.business.abstracts.TweetService;
import com.ozgekatirci.TwitterClone.dto.request.TweetRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tweet")
public class TweetController {
    private final TweetService tweetService;

    @GetMapping("/all")
    public ResponseEntity<List<TweetResponseDto>> getAllTweets() {
        return ResponseEntity.ok(tweetService.getAllTweets());
    }

    @GetMapping("/getById")
    public ResponseEntity<TweetResponseDto> getTweetById(@RequestParam Long id) {
        return ResponseEntity.ok(tweetService.getTweetByTweetId(id));
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<TweetResponseDto>> getTweetByUserId(@RequestParam Long id) {
        return ResponseEntity.ok(tweetService.getTweetsById(id));
    }

    @GetMapping("/getLikedUser")
    public ResponseEntity<List<UserResponseDto>> getLikedTweets(@RequestParam Long tweetId) {
        return ResponseEntity.ok(tweetService.getLikedUsersByTweetId(tweetId));

    }

    @GetMapping("/getRetweetedUsers")
    public ResponseEntity<List<UserResponseDto>> getRetweetedTweets(@RequestParam Long tweetId) {
        return ResponseEntity.ok(tweetService.getRetweetedUsersByTweetId(tweetId));

    }
    @GetMapping("/like")
    public ResponseEntity<Map<String, Object>> likeTweet(@RequestParam Long tweetId, @RequestParam  Long userId) {
        return ResponseEntity.ok(tweetService.likeTweet(tweetId, userId));
    }

    @GetMapping("/retweet")
    public ResponseEntity<Map<String, Object>> retweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        return ResponseEntity.ok(tweetService.createRetweet(tweetId, userId));
    }

    @PostMapping("/create")
    public ResponseEntity<TweetResponseDto> createTweet(@RequestParam Long userId, @RequestBody TweetRequestDto tweetRequestDto) {
        return ResponseEntity.ok(tweetService.createTweet(userId, tweetRequestDto));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTweet(@RequestParam Long tweetId) {
        return ResponseEntity.ok(tweetService.deleteTweet(tweetId));
    }

    @PutMapping("/update")
public ResponseEntity<TweetResponseDto> updateTweet(@RequestParam Long tweetId, @RequestBody TweetRequestDto tweetRequestDto) {
        return ResponseEntity.ok(tweetService.updateTweet(tweetId, tweetRequestDto));
    }


}
