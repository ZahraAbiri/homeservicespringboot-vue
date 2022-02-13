package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.dto.CommentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Comment;
import net.guides.springboot2.crud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/com")
public class CommentController {
	@Autowired
	CommentService commentService;



	@GetMapping("/comment")
	@ApiOperation(value = "get comments",
			response = Comment.class)
	public List<Comment> getAllComments() {
		return commentService.get();
	}


	@GetMapping("/comment/{id}")
	@ApiOperation(value = "get comments by id",
			response = Comment.class)
	public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Integer commentId)
			throws ResourceNotFoundException {
		Comment comment = commentService.getById(commentId);
		return ResponseEntity.ok().body(comment);
	}

	@PostMapping("/comm")
	@ApiOperation(value = "save comments ",
			response = Comment.class)
	public Comment createComment(@RequestBody CommentDto commentDto) {
		return commentService.save(commentDto);
	}

	@PutMapping("/commentss/{id}")
	@ApiOperation(value = "update comments by id",
			response = Comment.class)
	public ResponseEntity<Comment> updateComment(@PathVariable(value = "id") Integer commentId,
			@RequestBody Comment commentDetails) throws ResourceNotFoundException {
		Comment comment = commentService.updateById(commentId, commentDetails);
		return ResponseEntity.ok(comment);
	}

	@DeleteMapping("/comment/{id}")
	@ApiOperation(value = "delete comments by id",
			response = Comment.class)
	public Map<String, Boolean> deleteComment(@PathVariable(value = "id") Integer commentId)
			throws ResourceNotFoundException {
		Comment comment = commentService.deleteById(commentId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
