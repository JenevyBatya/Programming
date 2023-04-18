import java.util.Random;
public abstract class People {
    private Mood mood;
    People(double percentageOfMoodSwing){
        try{
            if (percentageOfMoodSwing<0 || percentageOfMoodSwing>10){
                throw new ProbabilityError();
            }
        } catch (ProbabilityError error){
            System.out.println("Формат задания настроения некорректен.\nБыло установлено новое значение в промежутке от 0 до 10");
            percentageOfMoodSwing=Math.random()*10;
        }
        if (percentageOfMoodSwing<=2){
            this.mood=Mood.HAPPY;
        }else if (percentageOfMoodSwing>=8){
            this.mood=Mood.UNHAPPY;
        }else{
            this.mood=Mood.LOST;
        }

    }
    public Mood getTheMood(){
        return this.mood;
    }
}
