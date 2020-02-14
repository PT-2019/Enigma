package common.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.Immutable;

/**
 * Une question
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 14/02/2020
 * @since 6.0 14/02/2020
 */
@ConvenienceClass
@Immutable
public class Question {

	private final String question;
	private final String answer;

	/**
	 * Une question
	 *
	 * @param question question
	 * @param answer   réponse
	 */
	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return this.question;
	}

	public String getAnswer() {
		return this.answer;
	}
}
