package pl.gornicki.postservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gornicki.postservice.config.Config;
import pl.gornicki.postservice.dto.CommentDto;
import pl.gornicki.postservice.dto.UserResponseDto;
import pl.gornicki.postservice.model.Comment;
import pl.gornicki.postservice.model.Post;
import pl.gornicki.postservice.repository.CommentRepository;
import pl.gornicki.postservice.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final Config config;
    private final String URL = "http://user-service:8080/users";

    public CommentDto addNewComment(CommentDto dto) {

        ResponseEntity<UserResponseDto> response = retrieveUserFromUserService(dto.getUserId());
        if(response.getBody()!=null) {
            Post post = postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post does not exist"));
            Comment comment = Comment.builder()
                    .post(post)
                    .userId(response.getBody().getId())
                    .content(dto.getContent())
                    .createdAt(LocalDateTime.now())
                    .build();
            commentRepository.save(comment);
            return dto;
        }
        throw new RuntimeException("User not found");
    }

    public CommentDto updateComment(CommentDto dto) {
        ResponseEntity<UserResponseDto> response = retrieveUserFromUserService(dto.getUserId());
        if(response.getBody()!=null) {
            Comment commentToUpdate = commentRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Comment does not exist"));
            commentToUpdate.setContent(dto.getContent());
            commentToUpdate.setUpdatedAt(LocalDateTime.now());
            commentRepository.save(commentToUpdate);
            return dto;
        }
        throw new RuntimeException("User not found");
    }

    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteAllCommentsByUserId(UUID userId) {
        commentRepository.deleteAllByUserId(userId);
    }

    private ResponseEntity<UserResponseDto> retrieveUserFromUserService(UUID dto) {
        return config.restTemplate()
                .getForEntity(
                        URL + "/" + dto,
                        UserResponseDto.class
                );
    }
}
