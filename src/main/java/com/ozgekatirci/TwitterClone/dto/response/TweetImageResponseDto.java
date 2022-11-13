package com.ozgekatirci.TwitterClone.dto.response;

import lombok.Data;

@Data
public class TweetImageResponseDto {
    private Long tweetId;
    private String imageId;
    private String imageUrl;
}
