package entities;

import java.util.List;
import java.util.UUID;

public class Task {

    private UUID id = UUID.randomUUID();
    private String name;
    private List<Question> questions;
    private double totalPoint;

    public Task() {
    }

    public Task(String name, List<Question> questions, double totalPoint) {
        this.name = name;
        this.questions = questions;
        this.totalPoint = totalPoint;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }
}
