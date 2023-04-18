public class Bimpo implements Cuties {
    @Override
    public void voice(boolean isEmptyness) {
        System.out.println("Голодное существо выскочило из-под стола, уставившись на мальчика большими глазами в надежде на получение еды");
        if (isEmptyness) {
            System.out.println("Увидев, что на столе ни крошки, песик метнулся в коридао, пропадая так же быстро, как и появившись");
        } else {

            System.out.println((Math.random() <= 0.5) ? "Тяв-тяв" : "Вуф-вуф");

        }

    }

    public void thoughts(boolean isEmpty) {
        if (!isEmpty) {
            System.out.println("Бимпо полностью проигнорировал Боссе.");
            System.out.println("Хоть с ракурса собаки и не было много видно, песик явно понимал, что где-то там еда явно есть, это главнее");
        }
    }

    @Override
    public boolean thiefSkill() {
        return (Math.random() <= 0.75);
    }


    @Override
    public void catchingZZZ(boolean givenMeat) {
        System.out.println((givenMeat) ? "В это же время Бимпо счастливо посапывает в ногах Малыша после вкусного обеда, свернувшись калачиком" :
                "В это же время, так и не получив ничего со стола, Бимпо плюхается в ногах Малыша и медленно погружается в дрему, все еще мечтая хотя бы о куске мяса");
    }

}
