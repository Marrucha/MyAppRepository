package com.crud.tasks;


import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Autowired
	TaskRepository taskRep;
    @Test
	public void contextLoads() {

/*    	Task newTask = new Task(2L,"test2","test3");
    	taskRep.save(newTask);*/
	}

}

