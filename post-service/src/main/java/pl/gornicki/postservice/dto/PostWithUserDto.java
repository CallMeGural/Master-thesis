package pl.gornicki.postservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostWithUserDto {
    private String title;
    private String content;
    private String username;
    private List<CommentWithoutPostDto> comments;
}
