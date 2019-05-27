package com.Ruban.ToDoApplication.ToDoApplication.Dao;

public class Users implements Comparable<Object> {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Users other = (Users) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + "]";
	}

	public Users(String name) {
		super();
		this.name = name;
	}

	public Users() {
		super();
	}

	@Override
	public int compareTo(Object o) {
		int x = 0;
		if(o instanceof Users)
		{
			Users u = (Users)o;
			x = this.name.compareTo(u.getName());
		}
		return x;
	}

}
