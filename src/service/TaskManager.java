package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    private int id = 100;

    public int hasId() {
        return ++id;
    }

    public Task createTask(Task task) {
        task.setId(hasId());
        task.setTaskStatus(TaskStatus.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    public Subtask createSubtask(Task task, int epicId) {
        task.setId(hasId());
        task.setTaskStatus(TaskStatus.NEW);
        Subtask subtask = new Subtask(task.getId(), task.getName(), task.getDescription(), task.getTaskStatus());
        subtask.setEpicId(epicId);
        subtasks.put(task.getId(), subtask);
        return subtask;
    }

    public Epic createEpic(Task task) {
        task.setId(hasId());
        task.setTaskStatus(TaskStatus.NEW);
        Epic epic = new Epic(task.getId(), task.getName(), task.getDescription(), task.getTaskStatus());
        epics.put(task.getId(), epic);
        return epic;
    }

    public void updateTask(int idOldTask, Task task) {
        tasks.put(idOldTask, task);
    }

    public void updateSubtask(int idOldTask, Subtask task) {
        subtasks.put(idOldTask, task);
    }

    public void updateEpic(int idOldTask, Epic task) {
        epics.put(idOldTask, task);
    }

    public void associateSubtaskToEpic() {
        for (Epic epic : epics.values()) {
            int epicId = epic.getId();
            for (Subtask subtask : subtasks.values()) {
                if (epicId == subtask.getEpicId()) epic.setSubTaskIdList(subtask.getId());
            }
        }
    }

    public Epic updateEpicStatus(int idEpic) {
        int countTaskStatusDone = 0;
        int countTaskStatusIsNew = 0;
        List<Integer> subtaskIdList = new ArrayList<>();

        subtaskIdList.addAll(getListSubtaskByEpic(idEpic));

        for (Integer subtaskId : subtaskIdList) {
            if (subtasks.get(subtaskId).getTaskStatus() == (TaskStatus.DONE)) {
                countTaskStatusDone++;
            } else if (subtasks.get(subtaskId).getTaskStatus() == (TaskStatus.NEW)) {
                countTaskStatusIsNew++;
            }
        }
        if (countTaskStatusDone == subtaskIdList.size()) {
            epics.get(idEpic).setTaskStatus(TaskStatus.DONE);
        } else if (countTaskStatusIsNew == subtaskIdList.size()) {
            epics.get(idEpic).setTaskStatus(TaskStatus.NEW);
        } else epics.get(idEpic).setTaskStatus(TaskStatus.IN_PROGRESS);

        Epic newEpic = new Epic(idEpic, epics.get(idEpic).getName(), epics.get(idEpic).getDescription(),
                epics.get(idEpic).getTaskStatus());
        return newEpic;
    }

    public Task taskStatusUpdate(int taskId, TaskStatus status) {
        Task newTask = tasks.get(taskId);
        newTask.setTaskStatus(status);
        return newTask;
    }

    public Subtask subtaskStatusUpdate(int subtaskId, TaskStatus status) {
        Subtask newTask = subtasks.get(subtaskId);
        newTask.setTaskStatus(status);
        return newTask;
    }

    public void printListTask(String typeTask) {
        switch (typeTask) {
            case "task":
                System.out.println(tasks);
                break;
            case "subtask":
                System.out.println(subtasks);
                break;
            case "epic":
                System.out.println(epics);
        }
    }

    public List<Integer> getListSubtaskByEpic(Integer epicId) {
        return epics.get(epicId).getSubTaskIdList();
    }

    public void removeAllTask(String typeTask) {
        switch (typeTask) {
            case "task":
                tasks.clear();
                break;
            case "subtask":
                subtasks.clear();
                break;
            case "epic":
                epics.clear();
        }
    }

    public HashMap getAllTask(String typeTask) {
        switch (typeTask) {
            case "task":
                return tasks;
            case "subtask":
                return subtasks;
            case "epic":
                return epics;
            default:
                return null;
        }
    }

    public Task getTask(int taskId, String typeTask) {
        switch (typeTask) {
            case "task":
                return tasks.get(taskId);
            case "subtask":
                return subtasks.get(taskId);
            case "epic":
                return epics.get(taskId);
            default:
                return null;
        }
    }

    public Task removeById(int taskId, String typeTask) {
        switch (typeTask) {
            case "task":
                return tasks.remove(taskId);
            case "subtask":
                return subtasks.remove(taskId);
            case "epic":
                return epics.remove(taskId);
            default:
                return null;
        }
    }

}
