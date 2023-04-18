import java.util.Random;
public class Main {
    public static void main(String[] args){
        System.out.println("Именно вы задаете начальное поведение персонажей.\nВыставив значение в диапозоне от 0 до 2 включительно, вы сделаете их грустыми или раздраженными.\nОт 2 до 8 - потерянными.\nОт 8 включительно до 10 - счастливыми.\nНа данный момент включен режим выбора рандомного значения.");
        System.out.println();
        LittleBoy littleBoy = new LittleBoy(Math.random()*10,Math.random()*10,"");
        Bimpo bimpo = new Bimpo();
        Bosse bosse = new Bosse(Math.random()*100);
        Story w = new Story(littleBoy,bosse,bimpo);

        w.go();

    }
}
