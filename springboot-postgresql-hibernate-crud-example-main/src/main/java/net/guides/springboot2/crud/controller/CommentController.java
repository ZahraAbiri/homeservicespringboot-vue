package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.guides.springboot2.crud.model.Comment;
import net.guides.springboot2.crud.repository.CommentDao;
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

	@GetMapping("/comment")
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}
//	@PostMapping("/employees")
//	public List<Employee> getAllEmployees1() {
//		return employeeRepository.findAll();
//	}
//
//	@PostMapping("/test")
//	public String test() {
//		return "test";
//	}

	@GetMapping("/comment/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Integer commentId)
			throws ResourceNotFoundException {
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id :: " + commentId));
		return ResponseEntity.ok().body(comment);
	}

	@PostMapping("/comm")
	public Comment createComment(@RequestBody Comment comment) {
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
