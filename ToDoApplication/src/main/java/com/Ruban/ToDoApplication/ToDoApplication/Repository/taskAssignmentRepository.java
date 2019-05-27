package com.Ruban.ToDoApplication.ToDoApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ruban.ToDoApplication.ToDoApplication.Models.TaskAssignment;

public interface taskAssignmentRepository extends JpaRepository<TaskAssignment, Integer> {

}
