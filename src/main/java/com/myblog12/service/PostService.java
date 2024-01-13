package com.myblog12.service;

import com.myblog12.payload.PostDto;

public interface PostService {
   PostDto createPost(PostDto postDto);

   PostDto getPostById(Long id);
}
