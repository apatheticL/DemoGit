package com.nhatle.demosqlline;

public class Store {
    private int id;
    private String name;
    private  String content;

    public Store(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
    private Store(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
