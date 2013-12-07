package de.croggle.game.model;

/**
 * 
 */
class MultipleChoiceLevel extends Level {
	
	
	private Board[] answers;
	private int correctAnswer; //TODO: wollen wir speichern was das richtige ist, oder auswerten und schauen ob der Term upmatcht?
    
    public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}
}
