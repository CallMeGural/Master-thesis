package pl.gornicki.postservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String content;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
    private UUID userId;
    private LocalDateTime createdAt;
    private int numberOfViews;
}
