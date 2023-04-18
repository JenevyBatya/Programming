import java.util.Random;
public class Story {


    private LittleBoy littleBoy;
    private Bosse bosse;
    private Bimpo bimpo;

    Story(LittleBoy littleBoy,Bosse bosse,Bimpo bimpo){

        this.littleBoy=littleBoy;
        this.bosse=bosse;
        this.bimpo=bimpo;

    }
    public void go() {
        // Локальный вложенный класс (нестатический)
        class Dad extends People {
            Dad(double percentageOfDadsMoodSwing) {
                super(percentageOfDadsMoodSwing);
            }
            private double pointsForOffendingLittleBoy;

            public double getPointsForOffendingLittleBoy() {
                return pointsForOffendingLittleBoy;
            }

            //TODO phrases for his attitude towards the uncle
            public void hisMood() {
                switch (getTheMood()) {
                    case LOST -> {
                        System.out.println("В последнее время папа ходил каким-то напряженным. На вопросы детей отнекивался, но мама сказала, что он переживает насчет своего дальнего родственника Юлиуса. Тот каждый год приезжал в Стокгольм, чтобы посоветоваться со своим врачом и погостить у Свантесонов.\nПапа его недолюдливал, но отсутвие вестей и ответа на звонки его немного пугало.");
                        pointsForOffendingLittleBoy = 5;
                    }
                    case HAPPY -> {
                        System.out.println("Папа в последнее время ходил таким счастливым. Как нам он сам сказал после внезапного звонка рано утром, его дальний родственник Дядя Юлиус не сможет приехать в этому году к нам в гости по каким-то уж очень странным причинам. Папа его на дух не переносил, особенно его остановку в нашей кватире длиной в две недели.\nОн так загорелся счастьем, что совсем не замечал следы пребывания их другого гостя, и Малышу это было на руку. Не видел отец, не обращали внимания и Боссе с Бетан.");
                        pointsForOffendingLittleBoy = 0;
                    }
                    case UNHAPPY -> {
                        System.out.println("Папа весь день ходил угрюмый... К нам раз в год приезжал его дальний родственник Дядя Юлиус, посоветоваться со своим врачем да погостить у Свантесонов. \nИ все бы ничего, но тот был ужасно скуп и предпочитал останавливаться в нашей квартире недели так на две. Папа его на дух не переносил, поэтому искал всякий повод выпроводить его быстрее. И такой повод подспел как раз вовремя. Папа его начал активно спрашивать про Карлосона, а с ним Боссе с Бетан.");
                        pointsForOffendingLittleBoy = 10;
                    }
                }
            }

        }

        Dad dad = new Dad(littleBoy.getPercentageOfDadsMoodSwing());
        littleBoy.converterMoodyIntoPoints();
        dad.hisMood();
        littleBoy.setPointOfHappiness(littleBoy.getPointOfHappiness(), dad.getPointsForOffendingLittleBoy());
        //Статический вложенный класс

        LittleBoy.CarlosonProblem exp = new LittleBoy.CarlosonProblem();
        exp.explanation();


        littleBoy.enteringDiningRoom();
        littleBoy.lunchTime();
        bimpo.voice(littleBoy.isEmptyness());
        bosse.getScared(!littleBoy.isEmptyness());
        bimpo.thoughts(littleBoy.isEmptyness());
        littleBoy.takenFood(bimpo.thiefSkill());
        littleBoy.setAtLost();
        bosse.laugh(littleBoy.isAtLost());
        bimpo.catchingZZZ(littleBoy.isIfTheDishWasTaken());
        littleBoy.character(littleBoy.isAtLost());

        //Анонимный класс
        Mum mum = new Mum(){

            @Override
            public void talking() {
                System.out.println("Мама все это время продолжала говорить по телефону...");
            }

        };
        mum.talking();
    }



}
