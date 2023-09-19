package pl.gornicki.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gornicki.postservice.dto.CommentDto;
import pl.gornicki.postservice.service.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto comment) {
        return ResponseEntity.ok(commentService.addNewComment(comment));
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto comment) {
        return ResponseEntity.ok(commentService.updateComment(comment));
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteCommentsByUserId(@PathVariable UUID userId) {
        commentService.deleteAllCommentsByUserId(userId);
    }
}
