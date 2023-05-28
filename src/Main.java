import model.Epic;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        // Эпик с подзадачами
        Task task1 = new Task(1, "Переезд", "Подготовка к переезду", TaskStatus.NEW);
        task1 = taskManager.createEpic(task1);
        Task task2 = new Task(1, "Купить коробки", "Поехать в магазин за коробками", TaskStatus.NEW);
        task2 = taskManager.createSubtask(task2, task1.getId());
        Task task3 = new Task(1, "нанять машину", "выбрать удобную компанию по грузоперевозкам",
                TaskStatus.NEW);
        // две задачи
        task3 = taskManager.createSubtask(task3, task1.getId());
        Task task4 = new Task(1, "simple task1", "description 1", TaskStatus.NEW);
        task4 = taskManager.createTask(task4);
        Task task5 = new Task(1, "simple task1", "description 1", TaskStatus.NEW);
        task5 = taskManager.createTask(task5);
        // Эпик с одной подзадачей
        Task task6 = new Task(1, "Epic 2", "description Epic2", TaskStatus.NEW);
        task6 = taskManager.createEpic(task6);
        Task task7 = new Task(1, "subtask1", "description subtask1", TaskStatus.NEW);
        task7 = taskManager.createSubtask(task7, task6.getId());

        taskManager.associateSubtaskToEpic();// Связываю эпики с подзадачами т. к. пока не создали

        System.out.println();
        taskManager.printListTask("epic");
        taskManager.printListTask("subtask");
        taskManager.printListTask("task");
        System.out.println(taskManager.getListSubtaskByEpic(101));

        System.out.println();
        taskManager.subtaskStatusUpdate(102,TaskStatus.IN_PROGRESS);
        taskManager.taskStatusUpdate(104,TaskStatus.DONE);
        taskManager.subtaskStatusUpdate(107,TaskStatus.DONE);

        System.out.println();
        taskManager.printListTask("epic");
        taskManager.printListTask("subtask");
        taskManager.printListTask("task");

        System.out.println();
        System.out.println(taskManager.updateEpicStatus(101));
        System.out.println(taskManager.updateEpicStatus(106));

        System.out.println();
        taskManager.removeById(104,"task");
        taskManager.printListTask("task");
        taskManager.removeAllTask("epic");
        taskManager.printListTask("epic");
    }
}