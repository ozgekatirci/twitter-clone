package com.ozgekatirci.TwitterClone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name_surname")
    private String nameAndSurname;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location")
    private String location;

    @Column(name = "website")
    private String website;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private String birthdate;

    @Column(name = "profile_image")
    private String profilePicture;

    @Column(name = "background_image")
    private String backgroundPicture;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @Column(name = "language")
    private String language;

    @Column(name = "is_active",columnDefinition="boolean default false")
    private boolean isActive;

    @Column(name = "is_protected_profile",columnDefinition="boolean default false")
    private boolean isProtectedProfile;

    @Column(name = "is_verified",columnDefinition="boolean default false")
    private boolean isVerified;

    @Column(name = "tweet_count",columnDefinition = "int8 default 0")
    private int tweetCount;

    @Column(name = "following_count",columnDefinition = "int8 default 0")
    private int followingCount;

    @Column(name = "follower_count",columnDefinition = "int8 default 0")
    private int followerCount;

    @Column(name = "like_count",columnDefinition = "int8 default 0")
    private int likeCount;

    @Column(name = "retweet_count",columnDefinition = "int8 default 0")
    private int retweetCount;

    @Column(name = "media_tweet_count",columnDefinition = "int8 default 0")
    private int mediaTweetCount;

    @Column(name = "replies_count",columnDefinition = "int8 default 0")
    private int RepliesCount;

    @Column(name = "notifications_count",columnDefinition = "int8 default 0")
    private int notificationsCount;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "resetPasswordCode")
    private String resetPasswordCode;

    @ManyToMany
    private List<Tweet> tweets;

    @ManyToMany
    private List<LikeTweet> likedTweets;

    @ManyToMany
    private List<Retweet> retweets;

    @ManyToMany
    @JoinTable(name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> followings;

    @ManyToMany
    @JoinTable(name = "user_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followers;

    @ManyToMany
    @JoinTable(name = "user_muted",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "muted_id"))
    private List<User> mutedUsers;

    @ManyToMany
    @JoinTable(name = "user_blocked",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_id"))
    private List<User> blockedUsers;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_pinned_tweet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private Tweet pinnedTweet;

    @ManyToMany
    @JoinTable(name = "user_requested",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "requested_id"))
    private List<User> requestedUsers;

    public User(){
        this.joinDate= LocalDateTime.now().withNano(0);
    }


}
