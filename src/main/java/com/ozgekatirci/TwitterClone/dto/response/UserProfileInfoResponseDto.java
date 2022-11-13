package com.ozgekatirci.TwitterClone.dto.response;

import com.ozgekatirci.TwitterClone.model.LikeTweet;
import com.ozgekatirci.TwitterClone.model.Retweet;
import com.ozgekatirci.TwitterClone.model.Tweet;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserProfileInfoResponseDto {
    private Long id;
    private String nameAndSurname;
    private String username;
    private String location;
    private String bio;
    private String website;
    private String birthdate;
    private LocalDateTime joinDate;
    private Long tweetCount;
    private Long mediaTweetCount;
    private Long likeCount;
    private Long tweetAndRepliesCount;
    private Long notificationsCount;
    private ImageResponseDto profilePicture;
    private ImageResponseDto backgroundPicture;
    private Integer pinnedTweetId;
    private Integer followerCount;
    private Integer followingCount;
    private List<CommonFollowerResponseDto> sameFollowers;
    private boolean isProtectedProfile;
    private boolean mutedDirectMessages;
    private boolean isUserMuted;
    private boolean isUserBlocked;
    private boolean isMyProfileBlocked;
    private boolean isFollower;






















    @ManyToMany
    private List<Tweet> tweets;

    @OneToMany(mappedBy = "user")
    private List<LikeTweet> likeTweets;

    @OneToMany(mappedBy = "user")
    private List<Retweet> retweets;

}
