package com.Ruban.ToDoApplication.ToDoApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ruban.ToDoApplication.ToDoApplication.Models.taskManager;

@Repository
public interface taskManagerRepository extends JpaRepository<taskManager, Integer> {

}
