package entities;

import java.util.ArrayList;
import java.util.UUID;

public class UserAnswer {

	private Question question;
	private ArrayList<UUID> answersId;

	public UserAnswer() {
	}

	public UserAnswer(Question question, ArrayList<UUID> answersId) {
		this.question = question;
		this.answersId = answersId;
	}

	public UUID getAnswerId(int index) {
		return this.answersId.get(index);
	}
	
	public void setAnswerId(int index, UUID answerId) {
		this.answersId.set(index, answerId);
	}

	public ArrayList<UUID> getAnswersId() {
		return answersId;
	}

	public void setAnswersId(ArrayList<UUID> answersId) {
		this.answersId = answersId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
