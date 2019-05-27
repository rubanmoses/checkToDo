package com.Ruban.ToDoApplication.ToDoApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ruban.ToDoApplication.ToDoApplication.Dao.Users;
import com.Ruban.ToDoApplication.ToDoApplication.Models.TaskAssignment;
import com.Ruban.ToDoApplication.ToDoApplication.Models.taskManager;
import com.Ruban.ToDoApplication.ToDoApplication.Service.TodoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/ruban"})
public class MainControllerRuban {
//	@Autowired
//	taskManagerRepository managerRepository;
	@Autowired
	TodoService todoService;
	
	@GetMapping(path = {"/work"})
	public String workingCheck()
	{
		return "This Controller is Working!";
	}
	
	@GetMapping(path = {"/getTask"})
	public List<taskManager> getAllTask()
	{
//		managerRepository.save(new taskManager(1, "ruban", "Milk","Bring milk today","08/04/2019",TaskManagerConstants.NOT_DONE));
//		managerRepository.save(new taskManager(2, "moses","Food","Eat food today","08/04/2019",TaskManagerConstants.NOT_DONE));
//		managerRepository.save(new taskManager(3, "ruban", "Gym","Go to gym","08/04/2019",TaskManagerConstants.NOT_DONE));
		List<taskManager> l = todoService.fetchAllData();
//		for(taskManager t: managerRepository.findAll())
//		{
//			l.add(t);
//		}
		return l;
	}
	
	@PostMapping(path = {"/addTask"})
	public @ResponseBody taskManager addNewTask(@RequestBody taskManager task) 
	{
		System.out.println("Inside addnew user method");
		System.out.println("The value of user -> " +task);
		return todoService.addTasks(task);
	}
	
	@PostMapping(path = {"/assignTask"})
	public @ResponseBody TaskAssignment assignTask(@RequestBody TaskAssignment task) 
	{
		System.out.println("Inside addnew user method");
		System.out.println("The value of user -> " +task);
		return todoService.addAssignments(task);
	}
	
	@GetMapping(path = {"/getAssignments"})
	public List<TaskAssignment> getAllAssignments()
	{
		return todoService.getAllAssignments();
	}
	
	@GetMapping(path = {"/getOnlyUsers"})
	public List<Users> getOnlyUsers()
	{
		return todoService.allAvilableUsers();
	}
	
	@PostMapping(path = {"/addTaskWithTodayDate"})
	public @ResponseBody taskManager addNewTaskWithDate(@RequestBody taskManager task) 
	{
		System.out.println("Inside addnew user method");
		System.out.println("The value of user -> " +task);
		return todoService.addTasksWithTodayDate(task);
	}
	
	@GetMapping(path = {"/getOneUserInfo/{username}"})
	public List<taskManager> getOneUser(@PathVariable("username") String task) {
		//todoService.addInitialData();
		System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getAllTaskOfOneUser(task);
		return l;
	}
	
	@PutMapping(path ={"/changeStatus"})
	public List<taskManager> editUser(@RequestBody taskManager task) 
	{
		return todoService.changeStatus(task);
		//System.out.println("Changes done!");
	}
	
	@PutMapping(path ={"/acceptTask"})
	public List<TaskAssignment> acceptTheTask(@RequestBody TaskAssignment task) 
	{
		return todoService.taskAccepted(task);
		//System.out.println("Changes done!");
	}
	
	@PutMapping(path ={"/declineTask"})
	public List<TaskAssignment> declineTheTask(@RequestBody TaskAssignment task) 
	{
		return todoService.taskDeclined(task);
		//System.out.println("Changes done!");
	}
	
	
	@PutMapping(path ={"/changeStatusToIncomplete"})
	public List<taskManager> editUserToIncomplete(@RequestBody taskManager task) 
	{
		return todoService.changeStatusToIncomplete(task);
		//System.out.println("Changes done!");
	}
	
	@DeleteMapping(path ={"/{id}"})
	public void deleteTask(@PathVariable("id") taskManager task) 
	{
		System.out.println("Inside delete task method!");
		todoService.deleteTheUserTask(task);
	}
	
	@DeleteMapping(path ={"/deleteAssignment/{id}"})
	public void deleteAssignment(@PathVariable("id") TaskAssignment task) 
	{
		System.out.println("Inside delete task method!");
		todoService.deleteAssignment(task);
	}
	
	@GetMapping(path = {"/oneTask/{id}"})
	public taskManager getOneUser(@PathVariable("id") taskManager task) {		
		taskManager task2 = null;
		try
		{
			task2 = todoService.getOneTask(task.getId());
		}
		catch (NullPointerException e) {
			System.out.println("Null pointer Exception Man!");
		}
		return task2;
	}
	
	@PutMapping(path ={"/edit"})
	public void editTheTask(@RequestBody taskManager task) 
	{
		todoService.saveChanges(task);
		System.out.println("Changes done!");
	}
	
	@GetMapping(path = {"/getOneUserInfoSortByTaskNameAscending/{username}"})
	public List<taskManager> getOneUserSortByTaskNameAscending(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getTaskSortByTaskNameAscending(task);
		return l;
	}
	
	@GetMapping(path = {"/getFromAssignedTasks/{username}"})
	public List<TaskAssignment> getFromAssignedTasks(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<TaskAssignment> l = todoService.getAssignedTasksFrom(task);
		return l;
	}
	
	@GetMapping(path = {"/getToAssignedTasks/{username}"})
	public List<TaskAssignment> getToAssignedTasks(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<TaskAssignment> l = todoService.getAssignedTasksTo(task);
		return l;
	}
	
	@GetMapping(path = {"/getOneUserInfoSortByTaskNameDescending/{username}"})
	public List<taskManager> getOneUserSortByTaskNameDescending(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getTaskSortByTaskNameDescending(task);
		return l;
	}
	
	@GetMapping(path = {"/getOneUserInfoSortByDateAscending/{username}"})
	public List<taskManager> getOneUserSortByDateAscending(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getTaskSortByDateAscending(task);
		return l;
	}
	
	@GetMapping(path = {"/getOneUserInfoSortByDateDescending/{username}"})
	public List<taskManager> getOneUserSortByDateDescending(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getTaskSortByDateDescending(task);
		return l;
	}
	@GetMapping(path = {"/getOneUserInfoByTodayDate/{username}"})
	public List<taskManager> getOneUserByTodayDate(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getTodaysTask(task);
		return l;
	}
	@GetMapping(path = {"/getOneUserInfoCompletedTask/{username}"})
	public List<taskManager> getOneUserByCompletedTasks(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getCompletedTasks(task);
		return l;
	}
	
	@GetMapping(path = {"/getOneUserInfoIncompletedTask/{username}"})
	public List<taskManager> getOneUserByIncompletedTasks(@PathVariable("username") String task) {
		//todoService.addInitialData();
		//System.out.println("Inside getOne User's task Method!");
		List<taskManager> l = todoService.getIncompletedTasks(task);
		return l;
	}

}
