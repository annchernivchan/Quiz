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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Answer answer = (Answer) o;

		if (Double.compare(answer.weight, weight) != 0) return false;
		if (id != null ? !id.equals(answer.id) : answer.id != null) return false;
		return answerText != null ? answerText.equals(answer.answerText) : answer.answerText == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
		temp = Double.doubleToLongBits(weight);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
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

