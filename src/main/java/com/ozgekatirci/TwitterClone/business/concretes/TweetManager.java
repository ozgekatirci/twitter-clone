package com.ozgekatirci.TwitterClone.business.concretes;

import com.ozgekatirci.TwitterClone.business.abstracts.TweetService;
import com.ozgekatirci.TwitterClone.dto.request.TweetRequestDto;
import com.ozgekatirci.TwitterClone.dto.response.TweetResponseDto;
import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import com.ozgekatirci.TwitterClone.exceptions.ResourceNotFoundException;
import com.ozgekatirci.TwitterClone.model.LikeTweet;
import com.ozgekatirci.TwitterClone.model.Retweet;
import com.ozgekatirci.TwitterClone.model.Tweet;
import com.ozgekatirci.TwitterClone.model.User;
import com.ozgekatirci.TwitterClone.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TweetManager  implements TweetService {

    private final TweetRepository tweetRepository;

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final RetweetRepository retweetRepository;

    private final LikeTweetRepository likeTweetRepository;

    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    private User findUser(Long userId) {
        return (User) userRepository.findUserById(userId).
                orElseThrow(()->new ResourceNotFoundException("User not found with id " + userId,HttpStatus.NOT_FOUND));
    }
    private Tweet findTweet(Long tweetId) {
        Tweet tweet= (Tweet) tweetRepository.findTweetByTweetId(tweetId)
                .orElseThrow(()->new ResourceNotFoundException("Tweet not found with id " + tweetId,HttpStatus.NOT_FOUND));
        if (tweet.isDeleted()) {
            throw new ResourceNotFoundException("That Tweet has been deleted.", HttpStatus.BAD_REQUEST);
        }
        return tweet;
    }

    @Override
    public List<TweetResponseDto> getAllTweets() {
        Iterable<Tweet> tweetList = tweetRepository.findAll();
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (Tweet tweet : tweetList) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;

    }

    @Override
    public TweetResponseDto getTweetByTweetId(Long tweetId) {
        Tweet tweet=findTweet(tweetId);
        TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
        return tweetResponseDto;
    }

    @Override
    public List<TweetResponseDto> getTweetsById(Long userId) {
        User user=findUser(userId);
        List<Tweet> tweets = tweetRepository.findTweetsByTweetId(userId);
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }


    @Override
    public List<TweetResponseDto> getRepliesByTweetId(Long tweetId) {
        Tweet tweet=findTweet(tweetId);
        List<Tweet> replies = tweetRepository.findRepliesByTweetId(tweetId);
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (Tweet reply : replies) {
            TweetResponseDto tweetResponseDto = modelMapper.map(reply, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<TweetResponseDto> getQuotesByTweetId(Long tweetId) {
        Tweet tweet=findTweet(tweetId);
        List<Tweet> quotes = tweetRepository.findQuotesByTweetId(tweetId);
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (Tweet quote : quotes) {
            TweetResponseDto tweetResponseDto = modelMapper.map(quote, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }

    @Override
    public List<UserResponseDto> getLikedUsersByTweetId(Long tweetId) {
        Tweet tweet=findTweet(tweetId);
        List<LikeTweet>likeTweets=tweet.getLikeTweets();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        List<User> users=new ArrayList<>();

        for (LikeTweet likeTweet : likeTweets) {
            try {
                if(likeTweet.getLikedUser().getId()!=null){
                    User user = findUser(likeTweet.getLikedUser().getId());
                    users.add(user);
                    UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
                    userResponseDtos.add(userResponseDto);
                }
                else{
                    throw new ResourceNotFoundException("User not found with id " + likeTweet.getLikedUser().getId(),HttpStatus.NOT_FOUND);
                }

            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }

        }
        return userResponseDtos;

    }

    @Override
    public List<UserResponseDto> getRetweetedUsersByTweetId(Long tweetId) {

        List<Retweet> retweets=findTweet(tweetId).getRetweets();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (Retweet retweet: retweets) {
            User user=retweet.getTweet().getUser();
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        }
        return userResponseDtos;
    }


    @Override
    public List<TweetResponseDto> getMediaTweetsById(Long userId) {
        User user=findUser(userId);
        List<Tweet> tweets = tweetRepository.findMediaTweetsByTweetId(userId);
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
            tweetResponseDtos.add(tweetResponseDto);
        }
        return tweetResponseDtos;
    }


    @Override
    public List<TweetResponseDto> getTweetsWithVideo() {
        return null;
    }


    @Override
    public List<TweetResponseDto> searchTweets(String text) {
        return tweetRepository.findAllByContent(text);
    }


    @Override
    public TweetResponseDto createTweet(Long userId, TweetRequestDto tweetRequestDto) {
        Tweet tweet = modelMapper.map(tweetRequestDto, Tweet.class);
        tweetRepository.save(tweet);
        TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
        return tweetResponseDto;
    }


    @Override
    public Map<String, Object> createRetweet(Long tweetId, Long userId) {
        User user=findUser(userId);
        Tweet tweet=findTweet(tweetId);

        List<Retweet> retweets=user.getRetweets();
        for (Retweet retweet : retweets) {
            if (retweet.getTweet().getTweetId().equals(tweetId)) {
                throw new ResourceNotFoundException("You have already retweeted this tweet.", HttpStatus.BAD_REQUEST);
            }
        }
        Retweet newRetweet = new Retweet();
        newRetweet.setTweet(tweet);
        newRetweet.getRetweetedUsers().add(user);
        retweetRepository.save(newRetweet);
        userRepository.save(user);
        user.setRetweetCount(user.getRetweetCount() + 1);
        retweets.add(newRetweet);
        tweet.setRetweetCount(tweet.getRetweetCount() + 1);
        tweetRepository.save(tweet);
        return Map.of("message", "Tweet retweeted successfully");
    }


    @Override
    public TweetResponseDto createReplyTweet(Long tweetId, TweetRequestDto reply, Long userId) {
        User user = findUser(userId);
        Tweet tweet = findTweet(tweetId);

        Tweet replyTweet = new Tweet();
        replyTweet.setContent(reply.getContent());
        replyTweet.setUser(user);
        user.setRepliesCount(user.getRepliesCount() + 1);
        tweet.getReplies().add(replyTweet);
        tweet.setReplyCount(tweet.getReplyCount() + 1);
        tweetRepository.save(replyTweet);
        userRepository.save(user);
        tweetRepository.save(tweet);
        TweetResponseDto tweetResponseDto = modelMapper.map(replyTweet, TweetResponseDto.class);
        return tweetResponseDto;
    }

    @Override
    public TweetResponseDto createQuoteTweet(Long tweetId, TweetRequestDto quote, Long userId) {
        User user=findUser(userId);
        Tweet tweet=findTweet(tweetId);

        Tweet quoteTweet = new Tweet();
        quoteTweet.setContent(quote.getContent());
        quoteTweet.setQuoteTweet(tweet);
        tweetRepository.save(quoteTweet);

        tweet.setQuoteCount(tweet.getQuoteCount() + 1);
//        tweet.getQuotes().add(quoteTweet);
        tweetRepository.save(tweet);

        user.getTweets().add(quoteTweet);
        user.setTweetCount(user.getTweetCount() + 1);
        userRepository.save(user);

        TweetResponseDto tweetResponseDto = modelMapper.map(quoteTweet, TweetResponseDto.class);
        return tweetResponseDto;
    }


    @Override
    public Map<String, Object> likeTweet(Long tweetId, Long userId) {
        User user=findUser(userId);
        Tweet tweet=findTweet(tweetId);

        List<LikeTweet> likedTweets = user.getLikedTweets();
        for (LikeTweet likedTweet : likedTweets) {
            if (likedTweet.getTweet().getTweetId().equals(tweetId)) {
                throw new ResourceNotFoundException("You have already liked this tweet.", HttpStatus.BAD_REQUEST);
            }
        }
        userRepository.save(user);
        user.setLikedTweets(likedTweets);
        user.setLikeCount(user.getLikeCount() + 1);
        return Map.of("message", "Tweet liked successfully");
    }

    @Override
    public TweetResponseDto updateTweet(Long tweetId, TweetRequestDto tweetRequestDto) {
        Tweet tweet = findTweet(tweetId);
        tweet.setContent(tweetRequestDto.getContent());
        tweetRepository.save(tweet);
        TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
        return tweetResponseDto;
    }

    @Override
    public String deleteTweet(Long tweetId) {
        Tweet tweet = findTweet(tweetId);
        tweet.setDeleted(true);
        tweetRepository.save(tweet);
        return "Tweet deleted successfully";

    }

    @Override
    public Map<String, Object> undoRetweet(Long tweetId, Long userId) {
        User user=findUser(userId);
        Tweet tweet=findTweet(tweetId);

        List<Retweet> retweets=user.getRetweets();
        for (Retweet retweet : retweets) {
            if (retweet.getTweet().getTweetId().equals(tweetId)) {
                retweets.remove(retweet);
                retweetRepository.delete(retweet);
                user.setRetweetCount(user.getRetweetCount() - 1);
                tweet.setRetweetCount(tweet.getRetweetCount() - 1);
                tweetRepository.save(tweet);
                userRepository.save(user);
                return Map.of("message", "Retweet undone successfully");
            }
        }
        throw new ResourceNotFoundException("You have not retweeted this tweet.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Map<String, Object> undoLike(Long tweetId, Long userId) {
        User user=findUser(userId);
        Tweet tweet=findTweet(tweetId);

        List<LikeTweet> likedTweets = user.getLikedTweets();
        for (LikeTweet likedTweet : likedTweets) {
            if (likedTweet.getTweet().getTweetId().equals(tweetId)) {
                likedTweets.remove(likedTweet);
                likeTweetRepository.delete(likedTweet);
                user.setLikeCount(user.getLikeCount() - 1);
                userRepository.save(user);
                return Map.of("message", "Like undone successfully");
            }
        }
        throw new ResourceNotFoundException("You have not liked this tweet.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean isLiked(Long tweetId, Long userId) {
        User user=findUser(userId);
        List<LikeTweet> likedTweets = user.getLikedTweets();
        for (LikeTweet likedTweet : likedTweets) {
            if (likedTweet.getTweet().getTweetId().equals(tweetId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRetweeted(Long tweetId, Long userId) {
        User user=findUser(userId);
        List<Retweet> retweets = user.getRetweets();
        for (Retweet retweet : retweets) {
            if (retweet.getTweet().getTweetId().equals(tweetId)) {
                return true;
            }
        }
        return false;
    }




}

