package com.Ruban.ToDoApplication.ToDoApplication.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskAssignment implements Comparable<Object> {
	@Id
	int id;
	String fromuser;
	String touser;
	String task;
	String taskDetails;
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromuser == null) ? 0 : fromuser.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((taskDetails == null) ? 0 : taskDetails.hashCode());
		result = prime * result + ((touser == null) ? 0 : touser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskAssignment other = (TaskAssignment) obj;
		if (fromuser == null) {
			if (other.fromuser != null)
				return false;
		} else if (!fromuser.equals(other.fromuser))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (taskDetails == null) {
			if (other.taskDetails != null)
				return false;
		} else if (!taskDetails.equals(other.taskDetails))
			return false;
		if (touser == null) {
			if (other.touser != null)
				return false;
		} else if (!touser.equals(other.touser))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TaskAssignment [id=" + id + ", fromuser=" + fromuser + ", touser=" + touser + ", task=" + task
				+ ", taskDetails=" + taskDetails + ", status=" + status + "]";
	}
	public TaskAssignment(int id, String fromuser, String touser, String task, String taskDetails, String status) {
		super();
		this.id = id;
		this.fromuser = fromuser;
		this.touser = touser;
		this.task = task;
		this.taskDetails = taskDetails;
		this.status = status;
	}
	public TaskAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Object o) {
		int x =0;
		if(o instanceof TaskAssignment)
		{
			TaskAssignment t = (TaskAssignment)o;
			x = this.task.compareTo(t.task);
		}
		return x;
	}

}
