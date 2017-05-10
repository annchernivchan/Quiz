package entities;

import java.util.UUID;

public class Answer {

	private UUID id = UUID.randomUUID();
	private String answerText;
	private double weight;

	public Answer() {
	}

	public Answer(String answerText) {
		this.answerText = answerText;
	}

	public Answer(String answerText, double weight) {
		this.answerText = answerText;
		if (weight <= 100.0) this.weight = weight;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public boolean isRight() {
		return this.weight != 0;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", answerText='" + answerText + '\'' +
				", weight=" + weight +
				'}';
	}
}

