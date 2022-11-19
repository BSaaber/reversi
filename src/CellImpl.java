public class CellImpl implements Cell {
    private final Integer price;
    private CellType cellType;

    public CellImpl(CellType cellType, Integer price) {
        this.price = price;
        this.cellType = cellType;
    }

    @Override
    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}
