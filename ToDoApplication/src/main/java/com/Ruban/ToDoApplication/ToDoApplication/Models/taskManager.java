package com.Ruban.ToDoApplication.ToDoApplication.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class taskManager implements Comparable<Object> {
	@Id
	int id;
	String username;
	String taskname;
	String taskDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "taskManager [id=" + id + ", username=" + username + ", taskname=" + taskname + ", taskDetails="
				+ taskDetails + ", date=" + date + ", status=" + status + "]";
	}
	public taskManager(int id, String username, String taskname, String taskDetails, String date, String status) {
		super();
		this.id = id;
		this.username = username;
		this.taskname = taskname;
		this.taskDetails = taskDetails;
		this.date = date;
		this.status = status;
	}
	public taskManager() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taskDetails == null) ? 0 : taskDetails.hashCode());
		result = prime * result + ((taskname == null) ? 0 : taskname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		taskManager other = (taskManager) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (taskDetails == null) {
			if (other.taskDetails != null)
				return false;
		} else if (!taskDetails.equals(other.taskDetails))
			return false;
		if (taskname == null) {
			if (other.taskname != null)
				return false;
		} else if (!taskname.equals(other.taskname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	String date;
	String status;
	@Override
	public int compareTo(Object o) {
		int x = 0;
		if(o instanceof taskManager)
		{
			taskManager t = (taskManager)o;
			x = this.username.compareTo(t.username);
		}
		return x;
	}
	
}
