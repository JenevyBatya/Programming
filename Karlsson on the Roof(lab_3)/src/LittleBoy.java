import java.util.Random;
public class LittleBoy extends People implements BoysAtTheTable {
    private double percentageOfDadsMoodSwing;
    private String name;

    LittleBoy(double percentageOfMoodSwing, double percentageOfDadsMoodSwing,String name) {
        super(percentageOfMoodSwing);
        try{
            if (name.equals("")){
                throw new NameError();
            }
        }catch (NameError error){
            System.out.println("Формат задания имени некорректен. Малыш не получил индивидуальности и остался Малышом");
            name="Малыш";
        }
        this.name=name;
        this.percentageOfDadsMoodSwing = percentageOfDadsMoodSwing;
    }
    public double getPercentageOfDadsMoodSwing(){
        return percentageOfDadsMoodSwing;
    }


    private double pointOfHappiness;
    private boolean atLost;
    private boolean ifTheDishWasTaken;

    public void converterMoodyIntoPoints() {
        switch (getTheMood()) {
            case HAPPY -> pointOfHappiness = 20;
            case UNHAPPY -> pointOfHappiness = 4;
            case LOST -> pointOfHappiness = 10;
        }

    }




    public void enteringDiningRoom() {
        System.out.println();
        if (this.pointOfHappiness <= 4) {
            System.out.println(name + " разрывался между тем, чтобы остаться дома и охранять Карлосона, или все же поехать.");
        } else if (this.pointOfHappiness > 15) {
            System.out.println(name + " преисполнился решимостью защитить летающего друга.");
        } else {
            System.out.println(name + " был в сметениях. Ему все еще предстояло выбрать: остаться дома и охранять нового друга или все же отправиться в долгожданную поездку.");
        }
    }
    public void setPointOfHappiness(double hisPointsOfHappyness,double dadsPointsOfHappyness){
        this.pointOfHappiness=hisPointsOfHappyness-dadsPointsOfHappyness;
    }
    private int pick = new Random().nextInt(Food.values().length);
    private final Food food = Food.values()[pick];

    public void lunchTime() {
        System.out.println("Зайдя на кухню, мальчик сразу же сел напротив Боссе, любопытно рассматривая журнальчик в руках брата");
        switch (food) {
            case MEAT -> {
                System.out.println("На его месте уже стояла тарелка с теплыми телячьими отбивными");
                pointOfHappiness += 3;
            }
            case SOUP -> {
                System.out.println("Перед ним уже стояла порция еще тепленького супа");
                pointOfHappiness += 1;
            }
            case PASTRY -> {
                System.out.println("На середине стола стояла накрытая салфеткой тарелка - вчерашние пирожки с повидлом. Пока брат не смотрел, Малыш подвинул посудину чуть ближе к себе");
                pointOfHappiness += 8;
            }
            case NOTHING -> {
                System.out.println("Малыш помнил, что в холодильнике еще были остатки вчерашнего ужина, но есть было нельзя - свою порцию, хоть и в виде целой банки варенья, он отдал своему новому другу");
                pointOfHappiness -= 3;
            }

        }

    }
    public double getPointOfHappiness(){
        return pointOfHappiness;
    }
    public void setAtLost() {
        if (pointOfHappiness <= 10) {

            System.out.println(name + " не знал, стоит ли говорить семье говорить про свои переживания. Поборов себя, он все же объяснился, почему поездку он все же пропустит");
            atLost = true;
        } else {
            System.out.println("Оконачательно решив, что никуда не едет, " + name + " сказал всем за столов о своих намерениях");
            atLost = false;
        }
    }
    public boolean isAtLost() {
        return atLost;
    }
    public boolean isEmptyness() {
        return food == Food.NOTHING;

    }
    public void takenFood(boolean pastryIsStolen) {
        if (food == Food.MEAT && pointOfHappiness < 10) {
            System.out.println(name + " совсем расхотел есть от переживаний. Идея еды не прельщала, но порция все же не отправиться обратно в холодильник - тарелка целиком была отдана собаке"+"\n");
            ifTheDishWasTaken = true;

            return;

        } else if (food == Food.PASTRY && !(Math.random() <= 0.75)) {
            System.out.println(name + " не успел и пирожка взять, как юркий щенок уже в прыжке с открытой пастью пытался стащить крайнюю булочку." +"\n" +"Вот только стол был чуток высоковат для песика, и никакой вкусняшки стащить не получилось");
            System.out.println("Меньшее, что мог предложить Малыш в этой ситуации, это почесывания песика за ушком, но тот уже умчался в коридор"+"\n");
            return;
        } else if (food == Food.PASTRY && pastryIsStolen) {
            System.out.println(name + " не успел и булочки взять, как юркий щенок  не тлько одним прыжком умудрился достать до стола, "+"\n"+"но и извернулся так, что самый крайник пирожок ровно лег в открытую пасть зверя");
            System.out.println("Малыш с улыбкой посмотрел на место, куда только что метнулся Бимпо и принялся за свою порцию, все еще думая над ситуацией, точнее, как ее объяснить семье"+"\n");
            ifTheDishWasTaken = true;
            return;
        }else{
            System.out.println("Вот только до посудины было совсем не достать, а хозяин не показывал намерения дать что-то со стола"+"\n");
        }

        ifTheDishWasTaken = false;
    }
    public boolean isIfTheDishWasTaken() {
        return ifTheDishWasTaken;
    }
    public void character(boolean isSmitten){
        if (isSmitten){
            System.out.println(name + "был мальчиком милым и добрым, но уже взрослым не по годам. Он не знал, как поступить, разрываясь между моральным долгом и личным желанияем не пропустить поездку.");
            System.out.println("Смог бы кто помочье ему в этом нелегком деле...");
        }else{
            System.out.println("И хотя Малыш был мальчиком милым и добрым, он мог иногда вдруг удивительно заупрямиться.");
            System.out.println("Вот и сейчас он стал тверд как кремень и не проявлял никакой склонности вести переговоры.");
        }
    }
    @Override
    public void getScared(boolean barkedDog) {
        System.out.println(barkedDog ? "Ой! Бимпо! Не пугай так!" : "");
    }

    public static class CarlosonProblem{
        public void explanation(){
            System.out.println("Как он может бросить Карлсона одного именно в тот момент, когда он действительно ему нужен! И хотя Карлсон никакой не шпион, а просто Карлсон, опасность ему все равно угрожает, если люди начнут за ним охотиться, чтобы получить десять тысяч крон\n Малыш думал об этом все время, пока вытирал пол после великого наводнения. Кто знает, что кому может взбрести в голову. Вдруг они посадят Карлсона в клетку в зоопарке или придумают что-нибудь еще более ужасное. Во всяком случае они не дадут Карлсону спокойно жить в маленьком домике на крыше. Уж это точно!");
        }
    }

}
