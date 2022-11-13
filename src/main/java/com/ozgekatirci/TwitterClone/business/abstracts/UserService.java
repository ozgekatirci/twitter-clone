package com.ozgekatirci.TwitterClone.business.abstracts;

import com.ozgekatirci.TwitterClone.dto.request.UserProfileSettingsRequestDto;
import com.ozgekatirci.TwitterClone.dto.request.UserRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.ImageResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserProfileSettingsResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import com.ozgekatirci.TwitterClone.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserProfileSettingsResponseDto getUserById(Long id);
    UserResponseDto getUserByUsername(String username);
    List<TweetResponseDto> getTweetsById(Long id);
    List<TweetResponseDto> getUserLikedTweets(Long id);
    List<TweetResponseDto> getUserTweetsAndReplies(Long id);
    List<TweetResponseDto> getUserRepliedTweets(Long id);
    List<TweetResponseDto> getUserQuotedTweets(Long id);
    List<TweetResponseDto> getUserMediaTweets(Long id);

    Image uploadImage(MultipartFile multipartFile);
    List<ImageResponseDto> getUserTweetImages(Long userId);
    UserProfileSettingsResponseDto updateUserProfile(UserProfileSettingsRequestDto userProfileSettingsRequestDto);
    List<UserResponseDto> getFollowers(Long id);

    List<UserResponseDto> getFollowings(Long id);
    Boolean startUseTwitter();


    void unfollowUser(Long followerId, Long followingId);

    void blockUser(Long id);
    void unblockUser(Long id);

    void muteUser(Long id);

    void unmuteUser(Long id);

    void deleteAccount(Long id);

    void updatePassword(String password);

    void followUser(Long followerId, Long followingId);
}
