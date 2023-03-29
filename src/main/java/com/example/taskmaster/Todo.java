package com.example.taskmaster;

public class Todo {
    private String title;
    private String deadline;
    private String info;

    public Todo(String title, String deadline, String info) {
        this.title = title;
        this.deadline = deadline;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "[" + title + "][" +deadline + "][" + info;
    }
}
