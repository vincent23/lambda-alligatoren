package de.croggle.game;

/**
 * 
 */
class MultipleChoiceLevel implements Level {
	
	public MultipleChoiceLevel (Scene[] answers, int correctAnswer) {
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}
	
	private Scene[] answers;
	private int correctAnswer; //TODO: wollen wir speichern was das richtige ist, oder auswerten und schauen ob der Term upmatcht?
    
    public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}
}
