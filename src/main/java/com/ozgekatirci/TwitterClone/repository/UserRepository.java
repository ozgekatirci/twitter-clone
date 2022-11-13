package com.ozgekatirci.TwitterClone.repository;

import com.ozgekatirci.TwitterClone.dto.response.UserResponseDto;
import com.ozgekatirci.TwitterClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<UserResponseDto> findAllFollowersById(Long id);

    List<UserResponseDto> findAllFollowingsById(Long id);

    Optional<Object> findUserById(Long userId);


}

