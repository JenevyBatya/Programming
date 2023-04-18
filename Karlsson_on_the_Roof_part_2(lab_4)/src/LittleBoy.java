import java.util.Objects;
import java.util.Random;

public final class LittleBoy extends PeopleAtTheTable implements BoysAtTheTable {
    private final String name = "Малыш";

    LittleBoy(double percentagePointsOfHappiness) {
        super(percentagePointsOfHappiness);
        System.out.println();
        if (this.getPointsOfHapiness() == 4) {
            System.out.println(name + " разрывался между тем, чтобы остаться дома и охранять Карлосона, или все же поехать.");
        } else if (this.getPointsOfHapiness() == 20) {
            System.out.println(name + " преисполнился решимостью защитить летающего друга.");
        } else {
            System.out.println(name + " был в сметениях. Ему все еще предстояло выбрать: остаться дома и охранять нового друга или все же отправиться в долгожданную поездку.");
        }
    }

    double pointsOfHappiness = this.getPointsOfHapiness();

    int pick = new Random().nextInt(Food.values().length);
    private final Food food = Food.values()[pick];
    public Food new_food = food;


    public void getFoodSt(){
        System.out.println();
        System.out.println(new_food);
        System.out.println();
    }

    public void lunchTime() {
        System.out.println("Зайдя на кухню, мальчик сразу же сел напротив Боссе, любопытно рассматривая журнальчик в руках брата");
        switch (food) {
            case MEAT -> {
                System.out.println("На его месте уже стояла тарелка с теплыми телячьими отбивными");
                pointsOfHappiness += 3;
            }
            case SOUP -> {
                System.out.println("Перед ним уже стояла порция еще тепленького супа");
                pointsOfHappiness += 1;
            }
            case PASTRY -> {
                System.out.println("На середине стола стояла накрытая салфеткой тарелка - вчерашние пирожки с повидлом. Пока брат не смотрел, Малыш подвинул посудину чуть ближе к себе");
                pointsOfHappiness += 8;
            }
            case NOTHING -> {
                System.out.println("Малыш помнил, что в холодильнике еще были остатки вчерашнего ужина, но есть было нельзя - свою порцию, хоть и в виде целой банки варенья, он отдал своему новому другу");
                pointsOfHappiness -= 3;
            }

        }
    }

    private boolean isSmitten;


    public void smitten() {
        if (pointsOfHappiness <= 10) {

            System.out.println(name + " не знал, стоит ли говорить семье говорить про свои переживания. Поборов себя, он все же объяснился, почему поездку он все же пропустит");
            isSmitten = true;
        } else {
            System.out.println("Оконачательно решив, что никуда не едет, " + name + " сказал всем за столов о своих намерениях");
            isSmitten = false;
        }
    }

    public boolean isSmitten() {
        return isSmitten;
    }

    public void temp() {
        System.out.println(pointsOfHappiness);

    }

    public Food dish() {
        return food;
    }

    private boolean ifTheDishWasTaken;

    public boolean isEmptyness() {
        return food == Food.NOTHING;

    }

    public void takenFood(double pointsOfHappiness, boolean pastryIsStolen) {
        if (food == Food.MEAT && pointsOfHappiness < 10) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LittleBoy littleBoy = (LittleBoy) o;
        return Double.compare(littleBoy.pointsOfHappiness, pointsOfHappiness) == 0 && pick == littleBoy.pick && isSmitten == littleBoy.isSmitten && ifTheDishWasTaken == littleBoy.ifTheDishWasTaken && Objects.equals(name, littleBoy.name) && food == littleBoy.food;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pointsOfHappiness, pick, food, isSmitten, ifTheDishWasTaken);
    }
}

/*
 * lunchtime() - prints which of the dishes is on the table
 * dish() - returns which of the dishes is on the table
 * takenFood() - prints if the dish would be consumed by Bimpo
 * isIfTheDishWasTaken() - returns true if the dish is consumed
 */