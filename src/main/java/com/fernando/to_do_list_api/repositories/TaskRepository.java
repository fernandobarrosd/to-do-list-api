package com.fernando.to_do_list_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fernando.to_do_list_api.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    @Query("UPDATE Task t SET t.finish = :finish WHERE t.id = :taskID ")
    Task setFinish(Boolean finish, Long taskID);

    @Query("SELECT t FROM Task t WHERE t.finish = true")
    List<Task> findFinishTasks();

    @Query("SELECT t FROM Task t WHERE t.finish = false")
    List<Task> findNotFinishTasks();
}