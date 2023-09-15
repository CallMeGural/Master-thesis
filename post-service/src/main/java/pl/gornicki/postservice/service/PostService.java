package pl.gornicki.postservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gornicki.postservice.config.Config;
import pl.gornicki.postservice.dto.PostDto;
import pl.gornicki.postservice.dto.PostWithUserDto;
import pl.gornicki.postservice.dto.UserResponseDto;
import pl.gornicki.postservice.model.Post;
import pl.gornicki.postservice.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Config config;
    private final String URL = "http://user-service:8080/users";
    private final PostRepository postRepository;

    public List<Post> getPostsByUser(UUID userId) {
        return postRepository.findAllByUserId(userId);
    }

    @Transactional
    public PostWithUserDto getPostById(UUID postId) {
        Post post = postRepository.findPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setNumberOfViews(post.getNumberOfViews()+1);
        postRepository.save(post);

        ResponseEntity<UserResponseDto> response =
                config.restTemplate()
                        .getForEntity(
                                URL+"/"+post.getUserId(),
                                UserResponseDto.class
                        );
        if(response.getBody()!=null)
            return new PostWithUserDto(
                    post.getTitle(),
                    post.getContent(),
                    response.getBody().getUsername()
                    ,post.getComments()
            );
        throw new RuntimeException("User not found");
    }

    public PostDto addPost(PostDto dto) {

        ResponseEntity<UserResponseDto> response =
                config.restTemplate()
                        .getForEntity(
                                URL+"/"+dto.getUserId(),
                                UserResponseDto.class
                        );

        if(response.getBody()!=null) {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .userId(response.getBody().getId())
                    .comments(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .numberOfViews(0)
                    .build();
            postRepository.save(post);
            return dto;
        }
        throw new RuntimeException("User not found");
    }
}
