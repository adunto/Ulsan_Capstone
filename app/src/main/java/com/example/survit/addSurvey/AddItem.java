package com.example.survit.addSurvey;

public class AddItem {

    public String[] typeArray = {"주관식", "객관식"};
    private String question, type, ans1, ans2, ans3, ans4, ans5;

    public AddItem() {

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAns(String ans1, String ans2, String ans3, String ans4, String ans5){
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
    }

    public AddItem getItem() {
        return this;
    }

}
