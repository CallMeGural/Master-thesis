package pl.gornicki.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gornicki.postservice.dto.PostDto;
import pl.gornicki.postservice.dto.PostWithUserDto;
import pl.gornicki.postservice.model.Post;
import pl.gornicki.postservice.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostWithUserDto> getPostById(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto dto) {
        return ResponseEntity.ok(postService.addPost(dto));
    }
}
