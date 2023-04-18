public enum Food {
    MEAT("телячья отбивная"),
    PASTRY ("пирожки с повидлом"),
    NOTHING ("ничего"),
    SOUP ("суп");

    private String lunch;
    Food(String lunch){
        this.lunch=lunch;
    }
    @Override
    public String toString(){
        return lunch;
    }
}
