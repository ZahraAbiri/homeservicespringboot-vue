package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.dto.CommentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Comment;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.repository.CommentDao;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.ExpertDao;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ExpertDao expertDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Comment getById(Integer commentId) throws ResourceNotFoundException {
        Comment comment = commentDao.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id :: " + commentId));
        return comment;
    }

    @Override
    public Comment deleteById(Integer commentId) throws ResourceNotFoundException {
        Comment comment = commentDao.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + commentId));
        commentDao.delete(comment);
        return comment;
    }

    @Override
    public Comment updateById(Integer commentId, Comment commentDetailsommentDetails) throws ResourceNotFoundException {
        Comment comment = commentDao.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id :: " + commentId));
        comment.setDesciption(commentDetailsommentDetails.getDesciption());
        comment.setOrder(commentDetailsommentDetails.getOrder());
        comment.setCustomer(commentDetailsommentDetails.getCustomer());
        comment.setScore(commentDetailsommentDetails.getScore());
        comment.setExpert(commentDetailsommentDetails.getExpert());
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> get() {
        return commentDao.findAll();
    }

    @Override
    public Comment save(CommentDto commentDto) {
        Order order = new Order();
        Customer customer = new Customer();
        Expert expert = new Expert();
        Optional<Order> ord = orderDao.findById(commentDto.getOrderid());
        if (ord.isPresent()) {
            order = ord.get();
        }
        Optional<Expert> exp = expertDao.findById(commentDto.getExpertid());
        if (exp.isPresent()) {
            expert = exp.get();
        }
        Optional<Customer> cus = customerDao.findById(commentDto.getExpertid());
        if (cus.isPresent()) {
            customer = cus.get();
        }
        Comment comment = new Comment();
        comment.setCustomer(customer);
        comment.setExpert(expert);
        comment.setOrder(order);
        comment.setDesciption(commentDto.getDesciption());
        comment.setScore(commentDto.getScore());
        comment.setScore(commentDto.getScore());
        comment.setDesciption(commentDto.getDesciption());
      return   commentDao.save(comment);
    }
}
