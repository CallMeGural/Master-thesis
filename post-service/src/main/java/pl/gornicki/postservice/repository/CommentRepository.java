package pl.gornicki.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gornicki.postservice.model.Comment;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Modifying
    @Query(value = "DELETE FROM comment WHERE user_id = ?1",nativeQuery = true)
    void deleteAllByUserId(UUID userId);

    @Modifying
    @Query(value = "DELETE FROM comment WHERE post_id=?1",nativeQuery = true)
    void deleteAllByPostId(UUID id);
}
