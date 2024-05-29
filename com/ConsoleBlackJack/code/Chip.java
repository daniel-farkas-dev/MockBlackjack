public enum Chip {
    ONE(1),TEN(10), TWENTY_FIVE(25), HUNDRED(100), FIVE_HUNDRED(500), THOUSAND(1000);
    
    private int value;
    
    private Chip(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
