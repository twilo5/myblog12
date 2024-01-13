package com.myblog12.serviceImp;

import com.myblog12.entity.Post;
import com.myblog12.exception.ResourceNotFoundException;
import com.myblog12.payload.PostDto;
import com.myblog12.repository.PostRepository;
import com.myblog12.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImp implements PostService {
    private PostRepository postRepo;

    public PostServiceImp(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
       Post post = new Post();
       post.setTittle(postDto.getTittle());
       post.setDescription(postDto.getDescription());
       post.setContent(postDto.getContent());
        Post save = postRepo.save(post);
        PostDto dto=new PostDto();
       dto.setTittle(save.getTittle());
       dto.setDescription(save.getDescription());
       dto.setContent(save.getContent());
       return dto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found with id:" + id));
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTittle(post.getTittle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}
