package com.myblog12.controller;

import com.myblog12.payload.CommentDto;
import com.myblog12.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/comments?postId=1
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @RequestParam long postId
    ){
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/comments/1
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable long id){
commentService.deleteComment(id);
return new ResponseEntity<>("comment is deleted!!",HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto = commentService.updateComment(id, commentDto,postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
