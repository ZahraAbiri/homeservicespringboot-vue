package net.guides.springboot2.crud.service;

import lombok.Getter;
import lombok.Setter;
import net.guides.springboot2.crud.model.Comment;
import net.guides.springboot2.crud.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

//    public void updateComment(Comment comment) {
//        commentDao.update(comment);
//    }

    public void deleteComment(Comment comment) {
        commentDao.delete(comment);
    }

    public List<Comment> findAll() {
        return commentDao.findAll();
    }


    public Comment saveComment(Comment comment) {
       return commentDao.save(comment);
    }


}
