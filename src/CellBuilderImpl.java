public class CellBuilderImpl implements CellBuilder{

    @Override
    public Cell buildCell(CellType type, Integer price) {
        return new CellImpl(type, price);
    }
}
