package com.ozgekatirci.TwitterClone.repository;

import com.ozgekatirci.TwitterClone.dto.response.ImageResponseDto;
import com.ozgekatirci.TwitterClone.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<ImageResponseDto> findAllImagesById(Long userId);

}

