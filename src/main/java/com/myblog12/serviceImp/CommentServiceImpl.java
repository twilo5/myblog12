package com.myblog12.serviceImp;

import com.myblog12.entity.Comment;
import com.myblog12.entity.Post;
import com.myblog12.exception.ResourceNotFoundException;
import com.myblog12.payload.CommentDto;
import com.myblog12.repository.CommentRepository;
import com.myblog12.repository.PostRepository;
import com.myblog12.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found with " + postId));
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);


        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setEmail(saveComment.getEmail());
        dto.setText(saveComment.getText());
        return dto;

    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
       Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found for id!!:" + id));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment not found for id!!:" + id));
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment saveComment = commentRepository.save(c);
        return mapToDto(saveComment);

    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;

    }
}
