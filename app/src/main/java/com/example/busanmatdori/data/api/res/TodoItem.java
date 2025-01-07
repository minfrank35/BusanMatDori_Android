package com.example.busanmatdori.data.api.res;

//TODO : TEST용 API 응답 데이터
public class TodoItem {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 기본 생성자
    public TodoItem() {
    }

    // 모든 필드를 받는 생성자
    public TodoItem(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Getter / Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // toString() - 디버깅용
    @Override
    public String toString() {
        return "TodoItem{" +
               "userId=" + userId +
               ", id=" + id +
               ", title='" + title + '\'' +
               ", completed=" + completed +
               '}';
    }
}
