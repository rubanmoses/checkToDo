package com.Ruban.ToDoApplication.ToDoApplication.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ruban.ToDoApplication.ToDoApplication.Comparators.DateComparatorAscending;
import com.Ruban.ToDoApplication.ToDoApplication.Comparators.DateComparatorDecending;
import com.Ruban.ToDoApplication.ToDoApplication.Comparators.TaskNameComparator;
import com.Ruban.ToDoApplication.ToDoApplication.Comparators.TaskNameComparatorDecending;
import com.Ruban.ToDoApplication.ToDoApplication.Constants.TaskManagerConstants;
import com.Ruban.ToDoApplication.ToDoApplication.Dao.Users;
import com.Ruban.ToDoApplication.ToDoApplication.Models.TaskAssignment;
import com.Ruban.ToDoApplication.ToDoApplication.Models.taskManager;
import com.Ruban.ToDoApplication.ToDoApplication.Repository.taskAssignmentRepository;
import com.Ruban.ToDoApplication.ToDoApplication.Repository.taskManagerRepository;

@Service
public class TodoService {
	@Autowired
	taskManagerRepository managerRepository;
	@Autowired
	taskAssignmentRepository taskAssignment;
	int idValue = 11;
	int idAssign = 5;
	public void addInitialData()
	{
		managerRepository.save(new taskManager(1, "ruban", "Milk","Bring milk today","01/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(2, "moses","Food","Eat food today","02/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(3, "ruban", "Gym","Go to gym","03/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(4, "ruban", "Dog food","Bring it and feed pinky","04/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(5, "ruban", "Medicine","Do not take medicine for 3 days","05/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(6, "moses","Call Ram","Tell him about birthday","06/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(7, "moses","Wish Ruban birthday","Happy birthaday ruban","07/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(8, "george","PingPong","Play Ping Pong today","08/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(9, "george","Feed fish!","Put worms for fish","09/04/2019",TaskManagerConstants.NOT_DONE));
		managerRepository.save(new taskManager(10, "george","Cricket","Play Cricket with friends!","10/04/2019",TaskManagerConstants.NOT_DONE));
		taskAssignment.save(new TaskAssignment(1,"ruban","moses", "choco","Bring chocolate",TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED));
		taskAssignment.save(new TaskAssignment(2,"ruban","moses", "hat","Bring hat today",TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED));
		taskAssignment.save(new TaskAssignment(3,"moses","ruban", "Items","Bring Ration today",TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED));
		taskAssignment.save(new TaskAssignment(4,"moses","ruban", "Mobile","Charge my device",TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED));
	}
	public List<taskManager> fetchAllData()
	{
		addInitialData();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			list.add(t);
		}
		return list;
	}
	public taskManager addTasks(taskManager task)
	{
		try
		{
			String lowName = task.getUsername().toLowerCase();
			String d = task.getDate();
			String[] dateArray = d.split("-");
			String newDate = dateArray[2]+"/"+dateArray[1]+"/"+dateArray[0];
			task.setDate(newDate);
			System.out.println("The new date is -> " +newDate);
			task.setUsername(lowName);
			task.setId(idValue);
			idValue++;
			task.setStatus(TaskManagerConstants.NOT_DONE);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array index out of bound man!");
		}
		return managerRepository.save(task);
	}
	
	public TaskAssignment addAssignments(TaskAssignment task)
	{
		String to = task.getTouser().toLowerCase();
		task.setTouser(to);
		task.setId(idAssign);
		idAssign++;
		task.setStatus(TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED);
		return taskAssignment.save(task);
	}
	
	public List<TaskAssignment> getAllAssignments()
	{
		List<TaskAssignment> l = new ArrayList<TaskAssignment>();
		for(TaskAssignment t: taskAssignment.findAll())
		{
			l.add(t);
		}
		return l;
	}
	public taskManager addTasksWithTodayDate(taskManager task)
	{
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String simpleDate = simpleDateFormat.format(new Date());
			System.out.println("The simple date -> " +simpleDate);
			String lowName = task.getUsername().toLowerCase();
			task.setDate(simpleDate);
			task.setUsername(lowName);
			task.setId(idValue);
			idValue++;
			task.setStatus(TaskManagerConstants.NOT_DONE);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array index out of bound man!");
		}
		return managerRepository.save(task);
	}
	public List<taskManager> getAllTaskOfOneUser(String name)
	{
		String lname = name.toLowerCase();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname))
			{
				list.add(t);
			}
		}
		return list;
	}
	public List<taskManager> changeStatus(taskManager task)
	{
		task.setStatus(TaskManagerConstants.DONE);
		managerRepository.save(task);
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(task.getUsername()))
			{
				list.add(t);
			}
		}
		return list;
	}
	
	public List<TaskAssignment> taskAccepted(TaskAssignment task)
	{
		task.setStatus(TaskManagerConstants.ASSIGNMENT_ACCEPTED);
		taskAssignment.save(task);
		List<TaskAssignment> list = new ArrayList<TaskAssignment>();
		for(TaskAssignment t: taskAssignment.findAll())
		{
			if(t.getTouser().equals(task.getTouser()))
			{
				list.add(t);
			}
		}
		return list;
	}
	
	public List<TaskAssignment> taskDeclined(TaskAssignment task)
	{
		task.setStatus(TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED);
		taskAssignment.save(task);
		List<TaskAssignment> list = new ArrayList<TaskAssignment>();
		for(TaskAssignment t: taskAssignment.findAll())
		{
			if(t.getTouser().equals(task.getTouser()))
			{
				list.add(t);
			}
		}
		return list;
	}
	public List<taskManager> changeStatusToIncomplete(taskManager task)
	{
		task.setStatus(TaskManagerConstants.NOT_DONE);
		managerRepository.save(task);
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(task.getUsername()))
			{
				list.add(t);
			}
		}
		return list;
	}
	public void deleteTheUserTask(taskManager task)
	{
		managerRepository.delete(task);
	}
	public taskManager getOneTask(int id)
	{
		taskManager t = null;
		for(taskManager task: managerRepository.findAll())
		{
			if(task.getId() == id)
			{
				t=task;
			}
		}
		return t;
	}
	public taskManager saveChanges(taskManager t)
	{
		return managerRepository.save(t);
	}
	public List<taskManager> getTaskSortByTaskNameAscending(String name)
	{
		String lname = name.toLowerCase();
		TaskNameComparator nameComparator = new TaskNameComparator();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname))
			{
				list.add(t);
			}
		}
		Collections.sort(list,nameComparator);
		return list;
	}
	public List<taskManager> getTaskSortByTaskNameDescending(String name)
	{
		String lname = name.toLowerCase();
		TaskNameComparatorDecending nameComparator = new TaskNameComparatorDecending();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname))
			{
				list.add(t);
			}
		}
		Collections.sort(list,nameComparator);
		return list;
	}
	public List<taskManager> getTaskSortByDateAscending(String name)
	{
		String lname = name.toLowerCase();
		DateComparatorAscending dateComparatore = new DateComparatorAscending();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname))
			{
				list.add(t);
			}
		}
		Collections.sort(list,dateComparatore);
		return list;
	}
	public List<taskManager> getTaskSortByDateDescending(String name)
	{
		String lname = name.toLowerCase();
		DateComparatorDecending dateComparator = new DateComparatorDecending();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname))
			{
				list.add(t);
			}
		}
		Collections.sort(list,dateComparator);
		return list;
	}
	public List<taskManager> getTodaysTask(String name)
	{
		String lname = name.toLowerCase();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String simpleDate = simpleDateFormat.format(new Date());
		System.out.println("The simple date -> " +simpleDate);
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname) && t.getDate().equals(simpleDate))
			{
				list.add(t);
			}
		}
		return list;
	}
	public List<taskManager> getCompletedTasks(String name)
	{
		String lname = name.toLowerCase();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname) && t.getStatus().equals(TaskManagerConstants.DONE))
			{
				list.add(t);
			}
		}
		return list;
	}
	public List<taskManager> getIncompletedTasks(String name)
	{
		String lname = name.toLowerCase();
		List<taskManager> list = new ArrayList<taskManager>();
		for(taskManager t: managerRepository.findAll())
		{
			if(t.getUsername().equals(lname) && t.getStatus().equals(TaskManagerConstants.NOT_DONE))
			{
				list.add(t);
			}
		}
		return list;
	}
	
	public List<TaskAssignment> getAssignedTasksFrom(String name)
	{
		String lname = name.toLowerCase();
		List<TaskAssignment> l = new ArrayList<TaskAssignment>();
		for(TaskAssignment t: taskAssignment.findAll())
		{
			if(t.getFromuser().equals(lname))
			{
				if(t.getStatus().equals(TaskManagerConstants.ASSIGNMENT_NOT_ACCEPTED))
				{
					t.setStatus(TaskManagerConstants.ASSIGNMENT_REQUEST_SENT);
				}
				else
				{
					t.setStatus(TaskManagerConstants.ASSIGNMENT_ACCEPTED);
				}
				l.add(t);
			}
		}
		return l;
	}
	
	public List<TaskAssignment> getAssignedTasksTo(String name)
	{
		String lname = name.toLowerCase();
		List<TaskAssignment> l = new ArrayList<TaskAssignment>();
		for(TaskAssignment t: taskAssignment.findAll())
		{
			if(t.getTouser().equals(lname))
			{
				l.add(t);
			}
		}
		return l;
	}
	public void deleteAssignment(TaskAssignment task)
	{
		taskAssignment.delete(task);
	}
	public List<Users> allAvilableUsers()
	{
		Set<String> set = new HashSet<String>();
		for(taskManager t: managerRepository.findAll())
		{
			set.add(t.getUsername());
		}
		List<Users> list = new ArrayList<Users>();
		for(String s: set)
		{
			Users u = new Users(s);
			list.add(u);
		}
		return list;
	}
}
