package com.ozgekatirci.TwitterClone.business.abstracts;

import com.ozgekatirci.TwitterClone.dto.request.TweetRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;

import java.util.List;
import java.util.Map;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();
    TweetResponseDto getTweetByTweetId(Long tweetId);
    List<TweetResponseDto> getTweetsById(Long userId);

    List<TweetResponseDto> getRepliesByTweetId(Long tweetId);

    List<TweetResponseDto> getQuotesByTweetId(Long tweetId);

    List<UserResponseDto> getLikedUsersByTweetId(Long tweetId);

    List<UserResponseDto> getRetweetedUsersByTweetId(Long tweetId);

    List<TweetResponseDto> getMediaTweetsById(Long userId);


    List<TweetResponseDto> getTweetsWithVideo();


    List<TweetResponseDto> searchTweets(String text);

    TweetResponseDto createTweet(Long userId, TweetRequestDto tweetRequestDto);

    TweetResponseDto updateTweet(Long tweetId, TweetRequestDto tweetRequestDto);

    String deleteTweet(Long tweetId);

    Map<String, Object> likeTweet(Long tweetId,Long userId);


    Map<String, Object> createRetweet(Long tweetId, Long userId);

    TweetResponseDto createReplyTweet(Long tweetId, TweetRequestDto reply, Long userId);

    TweetResponseDto createQuoteTweet(Long tweetId, TweetRequestDto quote, Long userId);

    Map<String, Object> undoRetweet(Long tweetId, Long userId);

    Map<String, Object> undoLike(Long tweetId, Long userId);
    boolean isLiked(Long tweetId, Long userId);

    boolean isRetweeted(Long tweetId, Long userId);



}
