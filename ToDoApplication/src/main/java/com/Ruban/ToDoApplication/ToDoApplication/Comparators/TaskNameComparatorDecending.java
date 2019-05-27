package com.Ruban.ToDoApplication.ToDoApplication.Comparators;

import java.util.Comparator;

import com.Ruban.ToDoApplication.ToDoApplication.Models.taskManager;

public class TaskNameComparatorDecending implements Comparator<Object>
{
	@Override
	public int compare(Object o1, Object o2) {
		int x = 0;
		if(o1 instanceof taskManager && o2 instanceof taskManager)
		{
			taskManager t1 = (taskManager)o1;
			taskManager t2 = (taskManager)o2;
			x = t2.getTaskname().compareTo(t1.getTaskname());
		}
		return x;
	}
}