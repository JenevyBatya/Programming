public class Main {
    public static void main(String[] args){
        LittleBoy littleBoy = new LittleBoy(Math.random());
        Bosse bosse = new Bosse(Math.random());
        Bimpo bimpo = new Bimpo();

        littleBoy.lunchTime();
        littleBoy.getFoodSt();

        bimpo.voice(littleBoy.isEmptyness());
        bosse.getScared(!littleBoy.isEmptyness());
        bimpo.thoughts(littleBoy.isEmptyness());
        littleBoy.takenFood(littleBoy.getPointsOfHapiness(), bimpo.thiefSkill());
        littleBoy.smitten();
        bosse.laugh(littleBoy.isSmitten());
        bimpo.catchingZZZ(littleBoy.isIfTheDishWasTaken());
        littleBoy.character(littleBoy.isSmitten());


    }
}
