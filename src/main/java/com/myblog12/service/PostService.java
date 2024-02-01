package com.myblog12.service;

import com.myblog12.payload.PostDto;

import java.util.List;

public interface PostService {
   PostDto createPost(PostDto postDto);

   PostDto getPostById(Long id);


    List<PostDto> getAllPosts(int pageNo, int pageSize);
}
