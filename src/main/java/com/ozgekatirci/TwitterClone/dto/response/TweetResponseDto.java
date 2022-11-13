package com.ozgekatirci.TwitterClone.dto.response;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class TweetResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;

    private LocalDateTime tweetDate;

    private String tweetImage;

    private String tweetVideo;

    private String tweetGif;

    private int likeCount;

    private int retweetCount;

    private int replyCount;

    private int quoteCount;

    private String link;

    private String linkTitle;

    private String linkDescription;

    private String linkImage;

    private boolean isDeleted;

    private List<TweetImageResponseDto> images;
    

}
