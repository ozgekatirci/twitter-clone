package com.ozgekatirci.TwitterClone.business.concretes;


import com.ozgekatirci.TwitterClone.business.abstracts.UserService;
import com.ozgekatirci.TwitterClone.dto.request.UserProfileSettingsRequestDto;
import com.ozgekatirci.TwitterClone.dto.request.UserRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.ImageResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserProfileSettingsResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import com.ozgekatirci.TwitterClone.model.Image;
import com.ozgekatirci.TwitterClone.model.LikeTweet;
import com.ozgekatirci.TwitterClone.model.Retweet;
import com.ozgekatirci.TwitterClone.model.User;
import com.ozgekatirci.TwitterClone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;


@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final ImageRepository imageRepository;
    private final RetweetRepository retweetRepository;
    private final LikeTweetRepository likeTweetRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        Iterable<User> userList = userRepository.findAll();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : userList) {
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public UserProfileSettingsResponseDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserProfileSettingsResponseDto userInfoResponseDto = modelMapper.map(user.get(), UserProfileSettingsResponseDto.class);
            return userInfoResponseDto;
        }
        else{
            return null;
        }
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return userResponseDto;
    }

    @Override
    public List<TweetResponseDto> getTweetsById(Long id) {
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<TweetResponseDto> tweets = tweetRepository.findAllTweetsByTweetId(id);
        for (TweetResponseDto tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getUserLikedTweets(Long id) {
        User user= userRepository.findById(id).get();
        List<LikeTweet> likedTweets =user.getLikedTweets();
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (LikeTweet likedTweet : likedTweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(likedTweet.getTweet(), TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }
    @Override
    public List<TweetResponseDto> getUserRetweetedTweets(Long id) {
        User user= userRepository.findById(id).get();
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<Retweet> retweetedTweets = user.getRetweets();
        for (Retweet retweetedTweet : retweetedTweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(retweetedTweet.getTweet(), TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getUserTweetsAndReplies(Long id) {
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<TweetResponseDto> tweets = tweetRepository.findAllTweetsAndRepliesByTweetId(id);
        for (TweetResponseDto tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getUserRepliedTweets(Long id) {
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<TweetResponseDto> tweets = tweetRepository.findAllRepliedTweetsByTweetId(id);
        for (TweetResponseDto tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getUserQuotedTweets(Long id) {
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<TweetResponseDto> tweets = tweetRepository.findAllQuotedTweetsByTweetId(id);
        for (TweetResponseDto tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getUserMediaTweets(Long id) {
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        List<TweetResponseDto> tweets = tweetRepository.findAllMediaTweetsByTweetId(id);
        for (TweetResponseDto tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public Image uploadImage(MultipartFile multipartFile) {
        //Image image = new Image();
        //image.setImage(multipartFile);
        //imageRepository.save(image);
        return null;
    }

    @Override
    public List<ImageResponseDto> getUserTweetImages(Long userId) {
        List<ImageResponseDto> imageResponseDtos = new ArrayList<>();
        List<ImageResponseDto> images = imageRepository.findAllImagesById(userId);
        for (ImageResponseDto image : images) {
            ImageResponseDto imageResponseDto = modelMapper.map(image, ImageResponseDto.class);
            imageResponseDtos.add(imageResponseDto);
        }
        return imageResponseDtos;
    }

    @Override
    public UserProfileSettingsResponseDto updateUserProfile(UserProfileSettingsRequestDto userProfileSettingsRequestDto) {
        User user = userRepository.findByUsername(userProfileSettingsRequestDto.getUsername());
        user.setUsername(userProfileSettingsRequestDto.getUsername());
        user.setEmail(userProfileSettingsRequestDto.getEmail());
        user.setNameAndSurname(userProfileSettingsRequestDto.getNameAndSurname());
        user.setBio(userProfileSettingsRequestDto.getBio());
        user.setWebsite(userProfileSettingsRequestDto.getWebsite());
        user.setLocation(userProfileSettingsRequestDto.getLocation());
        user.setBirthdate(userProfileSettingsRequestDto.getBirthDate());
        user.setPhoneNumber(userProfileSettingsRequestDto.getPhoneNumber());
        userRepository.save(user);
        UserProfileSettingsResponseDto userProfileSettingsResponseDto = modelMapper.map(user, UserProfileSettingsResponseDto.class);
        return userProfileSettingsResponseDto;
    }


    @Override
    public List<UserResponseDto> getFollowers(Long id) {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        List<UserResponseDto> users = userRepository.findAllFollowersById(id);
        for (UserResponseDto user : users) {
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public List<UserResponseDto> getFollowings(Long id) {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        List<UserResponseDto> users = userRepository.findAllFollowingsById(id);
        for (UserResponseDto user : users) {
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public Boolean startUseTwitter() {
        return null;

    }

    @Override
    public void followUser(Long followerId, Long followingId) {


    }

    @Override
    public void saveUser(UserResponseDto userResponseDto) {
        User user = modelMapper.map(userResponseDto, User.class);
        userRepository.save(user);
    }



    @Override
    public void unfollowUser(Long followerId, Long followingId) {

    }

    @Override
    public void blockUser(Long id) {

    }

    @Override
    public void unblockUser(Long id) {

    }

    @Override
    public void muteUser(Long id) {

    }

    @Override
    public void unmuteUser(Long id) {

    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public void updatePassword(String password) {

    }
}
