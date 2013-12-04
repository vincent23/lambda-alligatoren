package de.croggle.game.model;

/**
 * @opt all
 * @node class
 */
class MultipleChoiceLevel extends Level {
	
	
	private Scene[] answers;
	private int correctAnswer; //TODO: wollen wir speichern was das richtige ist, oder auswerten und schauen ob der Term upmatcht?
    
    public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}
}
