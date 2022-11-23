public enum CellType {
    WHITE('W'),
    BLACK('B'),
    NONE(' '),
    NEXT_MOVE('.');

    private final Character value;

    CellType(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}