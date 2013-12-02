class MultipleChoiceLevel extends Level {
    LambdaTerm answer1;
    LambdaTerm answer2;
    LambdaTerm answer3;
    int correctAnswer; //TODO: wollen wir speichern was das richtige ist, oder auswerten und schauen ob der Term upmatcht?
    
    void selectAnswer(LambdaTerm selection) {
        currentTerm = selection;
        //do whatever it is to start Simulation OR check 
        //if selection evaluates to goalTerm OR if answernumber is the correctAnswer :: Level is won
    }