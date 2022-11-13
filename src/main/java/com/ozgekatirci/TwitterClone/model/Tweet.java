package com.ozgekatirci.TwitterClone.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long tweetId;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime tweetDate;

    @Column(name = "image")
    private String tweetImage;

    @Column(name = "video")
    private String tweetVideo;

    @Column(name = "gif")
    private String tweetGif;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "retweet_count")
    private int retweetCount;

    @Column(name = "reply_count")
    private int replyCount;

    @Column(name = "quote_count")
    private int quoteCount;

    @Column(name="link")
    private String link;

    @Column(name="link_title")
    private String linkTitle;

    @Column(name="link_description")
    private String linkDescription;

    @Column(name="link_image")
    private String linkImage;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tweet")
    private List<LikeTweet> likeTweets;

    @OneToMany(mappedBy = "tweet")
    private List<Retweet> retweets;

    @OneToMany
    private List<Image> images;

    @ManyToMany
    @JoinTable(name = "tweet_reply",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id"))
    private List<Tweet> replies;

    @OneToMany
    @JoinTable(name = "quotes_of_tweet",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private List<Tweet> quotesOfTweet;

    @OneToOne
    @JoinTable(name = "quote_tweet",
            joinColumns = @JoinColumn(name = "tweets_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_tweet_id"))
    private Tweet quoteTweet;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_pinned_tweet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private Tweet pinnedTweet;

   public Tweet(){
       this.tweetDate= LocalDateTime.now().withNano(0);
       this.likeTweets=new ArrayList<>();
       this.replies=new ArrayList<>();
       this.quotesOfTweet=new ArrayList<>();
       this.images=new ArrayList<>();
   }

}
