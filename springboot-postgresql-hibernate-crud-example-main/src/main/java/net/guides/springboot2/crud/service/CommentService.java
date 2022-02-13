package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.CommentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Comment;

import java.util.List;

public interface CommentService {
    public Comment getById(Integer CommentId) throws ResourceNotFoundException;
    public Comment deleteById(Integer CommentId) throws ResourceNotFoundException;
    public Comment updateById(Integer CommentId,Comment CommentDetails) throws ResourceNotFoundException;
    public List<Comment> get();

    public Comment save(CommentDto commentDto);
}
