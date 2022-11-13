package com.ozgekatirci.TwitterClone.repository;

import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  TweetRepository extends JpaRepository<Tweet, Long> {
    List<TweetResponseDto> findAllTweetsByTweetId(Long id);

    List<TweetResponseDto> findAllTweetsAndRepliesByTweetId(Long id);

    List<TweetResponseDto> findAllRepliedTweetsByTweetId(Long id);

    List<TweetResponseDto> findAllQuotedTweetsByTweetId(Long id);

    List<TweetResponseDto> findAllMediaTweetsByTweetId(Long id);

    Optional<Object> findTweetByTweetId(Long tweetId);

    List<Tweet> findTweetsByTweetId(Long userId);

    List<Tweet> findRepliesByTweetId(Long tweetId);

    List<Tweet> findQuotesByTweetId(Long tweetId);



    List<Tweet> findMediaTweetsByTweetId(Long userId);


    List<TweetResponseDto> findAllByContent(String text);
}

