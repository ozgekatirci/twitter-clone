package com.ozgekatirci.TwitterClone.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "like_tweet")
public class LikeTweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "like_date")
    private LocalDateTime likeDate;


    @ManyToMany
    @JoinColumn(name = "user_id")
    private List<User> likedUsers;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public LikeTweet() {
        this.likeDate=LocalDateTime.now().withNano(0);
    }


}
