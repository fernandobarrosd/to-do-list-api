package com.fernando.to_do_list_api.mappers;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.fernando.to_do_list_api.dtos.TaskRequest;
import com.fernando.to_do_list_api.models.Task;

@Component
public class TaskMapper {
    public Task toModel(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.name());
        task.setDescription(taskRequest.description());
        task.setFinish(false);
        task.setUpdatedAt(LocalDate.now());
        task.setCreatedAt(LocalDate.now());

        return task;
    }
}
