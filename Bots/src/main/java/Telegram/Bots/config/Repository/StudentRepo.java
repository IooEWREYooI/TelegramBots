package Telegram.Bots.config.Repository;

import Telegram.Bots.config.Entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentRepo extends CrudRepository<StudentEntity, Long> {
    @Query("SELECT u.id FROM StudentEntity u WHERE u.user_id = ?1")
    Long existByUserId(Long id);
}
