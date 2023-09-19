package pl.gornicki.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gornicki.postservice.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query(value = "SELECT * FROM post WHERE user_id = ?1",nativeQuery = true)
    List<Post> findAllByUserId(UUID userId);

    @Modifying
    @Query(value = "DELETE FROM post WHERE user_id = ?1",nativeQuery = true)
    void deleteAllByUserId(UUID userId);
}
