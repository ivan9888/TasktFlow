package com.unosq.taskflow.repositories;

import com.unosq.taskflow.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //String can deduct the query by the name of the method:
    List<Task> findByUserId(Long userId);

    //This also works with personalized consultation.
    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.status = :status")
    List<Task> findActiveTasksByUser(@Param("userId") Long userId, @Param("status") String status);
}