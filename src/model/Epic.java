package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private ArrayList<Integer> subTaskIdList = new ArrayList<>();

    public Epic(int id, String name, String description, TaskStatus taskStatus) {
        super(id, name, description, taskStatus);
    }

    public void setSubTaskIdList(int subTaskIdList) {
        this.subTaskIdList.add(subTaskIdList);
    }

    public ArrayList<Integer> getSubTaskIdList() {
        return subTaskIdList;
    }

    public Integer getSizeSubtaskList() {
        return this.subTaskIdList.size();
    }

    @Override
    public String toString() {
        return "Epic{" +
                " id=" + getId() +
                ", name=" + getName() +
                ", description " + getDescription() +
                ", taskStatus=" + getTaskStatus() +
                '}';
    }
}
