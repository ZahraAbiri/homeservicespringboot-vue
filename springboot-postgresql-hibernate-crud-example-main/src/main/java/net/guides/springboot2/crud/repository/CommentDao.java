package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {
//    void update(Comment comment);
}
