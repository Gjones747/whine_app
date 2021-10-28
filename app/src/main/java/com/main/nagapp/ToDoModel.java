package com.main.nagapp;

public class ToDoModel {
    String question;
    String yesMessage;
    String noMessage;
    String time;

    @Override
    public String toString() {
        return "ToDoModel{" +
                "question='" + question + '\'' +
                ", yesMessage='" + yesMessage + '\'' +
                ", noMessage='" + noMessage + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getYesMessage() {
        return yesMessage;
    }

    public void setYesMessage(String yesMessage) {
        this.yesMessage = yesMessage;
    }

    public String getNoMessage() {
        return noMessage;
    }

    public void setNoMessage(String noMessage) {
        this.noMessage = noMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ToDoModel(String question, String yesMessage, String noMessage, String time) {
        this.question = question;
        this.yesMessage = yesMessage;
        this.noMessage = noMessage;
        this.time = time;
    }


}
