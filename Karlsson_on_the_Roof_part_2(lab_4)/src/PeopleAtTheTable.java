public abstract class PeopleAtTheTable {
    double pointsOfHappiness;
    PeopleAtTheTable(double percentagePointsOfHappiness){
        if (percentagePointsOfHappiness<=0.2){
            this.pointsOfHappiness = 20;
        } else if (percentagePointsOfHappiness>=0.8){
            this.pointsOfHappiness = 4;
        } else {
            this.pointsOfHappiness = 10;
        }
    }
    public double getPointsOfHapiness(){
        return pointsOfHappiness;
    }
}
