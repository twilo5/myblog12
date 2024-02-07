package com.myblog12.controller;

import com.myblog12.payload.PostDto;
import com.myblog12.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
@PostMapping
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    http://localhost:8080/api/posts?id=1

    @GetMapping
    public ResponseEntity<PostDto>getPostById(@RequestParam Long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=5
@GetMapping("/getAll")
    public List<PostDto> getAllPosts(@RequestParam(name="pageNo",required = false,defaultValue = "0")int pageNo,
                                     @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize){
    List<PostDto> postDtos = postService.getAllPosts(pageNo, pageSize);
    return postDtos;
}
}

