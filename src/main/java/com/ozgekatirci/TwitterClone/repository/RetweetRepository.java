package com.ozgekatirci.TwitterClone.repository;

import com.ozgekatirci.TwitterClone.model.Retweet;
import com.ozgekatirci.TwitterClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
}

