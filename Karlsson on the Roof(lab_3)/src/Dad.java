public class Dad extends People{
    private Mood mood;
    Dad(double percentageOfMoodSwing){
        super(percentageOfMoodSwing);
        this.mood=getTheMood();

    }

    public void hisMood(){
        switch (getTheMood()){
            case LOST -> {}
            case HAPPY -> {}
            case UNHAPPY -> {}
        }
    }
}
