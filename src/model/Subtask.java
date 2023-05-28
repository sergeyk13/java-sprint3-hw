package model;

import java.util.ArrayList;
import java.util.List;

public class Subtask extends Task {
    private Integer epicId;


    public Subtask(int id, String name, String description, TaskStatus taskStatus) {
        super(id, name, description, taskStatus);
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(int id) {
        this.epicId = id;
    }
}
