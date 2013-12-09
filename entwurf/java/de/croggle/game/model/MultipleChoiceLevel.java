package de.croggle.game.model;

/**
 * a special type of level in which the player has to choose from several options, which of them evaluates into a 
 */
class MultipleChoiceLevel extends Level {
	
	
	private Board[] answers;
	private int correctAnswer; //TODO: wollen wir speichern was das richtige ist, oder auswerten und schauen ob der Term upmatcht?
    
    public boolean validateAnswer(int selection) {
		return selection == correctAnswer;
	}
}
