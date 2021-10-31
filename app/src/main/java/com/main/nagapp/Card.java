package com.main.nagapp;

public class Card {
    private String question;
    private String yes_response;
    private String no_response;
    private String time;

    public Card(String question, String yes_response, String no_response, String time) {
        this.question = question;
        this.yes_response = yes_response;
        this.no_response = no_response;
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public String getYes_response() {
        return yes_response;
    }

    public String getNo_response() {
        return no_response;
    }

    public String getTime() {
        return time;
    }
}
