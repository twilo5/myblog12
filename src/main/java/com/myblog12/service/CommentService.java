package com.myblog12.service;

import com.myblog12.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,long postId);


    void deleteComment(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
