package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import net.guides.springboot2.crud.dto.CommentDto;
import net.guides.springboot2.crud.model.Comment;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.repository.CommentDao;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.ExpertDao;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/com")
public class CommentController {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ExpertDao expertDao;
	@Autowired
	private CustomerDao customerDao;
	@GetMapping("/comment")
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}


	@GetMapping("/comment/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Integer commentId)
			throws ResourceNotFoundException {
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id :: " + commentId));
		return ResponseEntity.ok().body(comment);
	}

	@PostMapping("/comm")
	public Comment createComment(@RequestBody CommentDto commentDto) {
		Order order=new Order();
		Customer customer=new Customer();
		Expert expert=new Expert();
		Optional<Order> ord = orderDao.findById(commentDto.getOrderid());
		if (ord.isPresent()) {
			order = ord.get();
		}Optional<Expert> exp = expertDao.findById(commentDto.getExpertid());
		if (exp.isPresent()) {
			expert = exp.get();
		}Optional<Customer> cus = customerDao.findById(commentDto.getExpertid());
		if (cus.isPresent()) {
			customer = cus.get();
		}
		Comment comment=new Comment();
		comment.setCustomer(customer);
		comment.setExpert(expert);
		comment.setOrder(order);
		comment.setDesciption(commentDto.getDesciption());
		comment.setScore(commentDto.getScore());
		return commentDao.save(comment);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable(value = "id") Integer commentId,
			@RequestBody Comment commentDetails) throws ResourceNotFoundException {
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment not found for this id :: " + commentId));

		comment.setDesciption(commentDetails.getDesciption());
//		employee.setLastName(commentDetails.getLastName());
//		employee.setFirstName(commentDetails.getFirstName());
		final Comment updatedEmployee = commentDao.save(comment);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/comment/{id}")
	public Map<String, Boolean> deleteComment(@PathVariable(value = "id") Integer commentId)
			throws ResourceNotFoundException {
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + commentId));

		commentDao.delete(comment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
