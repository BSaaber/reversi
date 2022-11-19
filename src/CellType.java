public enum CellType {
    WHITE('W'),
    BLACK('B'),
    NONE(' ');

    private final Character value;

    CellType(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}