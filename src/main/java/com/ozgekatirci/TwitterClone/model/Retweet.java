package com.ozgekatirci.TwitterClone.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "table_retweet")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long retweetId;


    @Column(name = "retweet_date")
    private LocalDateTime retweetDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User retweetedUser;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public Retweet() {
        this.retweetDate=LocalDateTime.now().withNano(0);

    }


    public Retweet(Tweet tweet, User user) {
        this.tweet = tweet;
        this.retweetedUser= user;
        this.retweetDate=LocalDateTime.now().withNano(0);
    }
}
