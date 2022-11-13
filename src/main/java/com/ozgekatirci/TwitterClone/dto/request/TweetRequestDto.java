package com.ozgekatirci.TwitterClone.dto.request;


import com.ozgekatirci.TwitterClone.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class TweetRequestDto {
    private Long id;
    private String content;
    private List<Image> images;

}
