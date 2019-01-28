package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TaskRepository extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();

    Task findById(Long id);
}
