interface Field extends PrintableField {

    public Cell getCell(Integer i, Integer j);


    public default boolean checkCellExistence(Integer i, Integer j) {
        return i < getFieldSize() && j < getFieldSize() && i >= 0 && j >= 0;
    }
}
