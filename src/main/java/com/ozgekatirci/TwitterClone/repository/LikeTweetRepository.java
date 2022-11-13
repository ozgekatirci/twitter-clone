package com.ozgekatirci.TwitterClone.repository;

import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.model.LikeTweet;
import com.ozgekatirci.TwitterClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeTweetRepository extends JpaRepository<LikeTweet, Long> {

    List<TweetResponseDto> findAllLikedTweetsById(Long id);

}

