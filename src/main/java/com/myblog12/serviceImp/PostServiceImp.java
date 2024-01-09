package com.myblog12.serviceImp;

import com.myblog12.entity.Post;
import com.myblog12.payload.PostDto;
import com.myblog12.repository.PostRepository;
import com.myblog12.service.PostService;
import org.springframework.stereotype.Service;

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
}
