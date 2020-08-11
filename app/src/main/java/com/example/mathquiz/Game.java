package com.example.mathquiz;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Questions> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int score;


    private Questions currentQuestion;

    public Game()
    {
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestion=new Questions(10);
        questions=new ArrayList<Questions>();
    }

    public void makeNewQuestion()
    {
        currentQuestion=new Questions(totalQuestions*2+5);
        totalQuestions++;
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submittedAnswer)
    {
        boolean isCorrect;
        if(currentQuestion.getAnswer()==submittedAnswer)
        {
            numberCorrect++;
            isCorrect=true;
        }
        else
        {
            numberIncorrect++;
            isCorrect=false;
        }
        score=numberCorrect*10-numberIncorrect*30;
        return isCorrect;
    }





    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Questions getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Questions currentQuestion) {
        this.currentQuestion = currentQuestion;
    }



}
