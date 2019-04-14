package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskMapper taskMaper;
    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/getTasks")
    public List<TaskDto> getTasks(){
        return taskMaper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException{
        return taskMaper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));}

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/deleteTask")
    public void deleteTask(@RequestParam Long taskId)
    {service.deleteTaskById(taskId);}

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskMaper.mapToTaskDto(service.saveTask(taskMaper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks/createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        service.saveTask(taskMaper.mapToTask(taskDto));
    }

}

