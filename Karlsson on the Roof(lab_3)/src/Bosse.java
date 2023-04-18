public final class Bosse extends People implements BoysAtTheTable{
    private final String name = "Боссе";
    public Bosse(double percentagePointsOfHappiness) {
        super(percentagePointsOfHappiness);
    }
    public void laugh(boolean isSmitten){
        if (isSmitten){
            System.out.println(name + " рассхохотался ");
        }else{
            System.out.println(name + " рассхохотался от серьезного детского личика брата");
        }
    }
    @Override
    public void getScared(boolean barkedDog) {
        if (barkedDog){
            System.out.println("'Ты че разгавкался?!' - крикнул на собаку Боссе");
        }
    }
}
