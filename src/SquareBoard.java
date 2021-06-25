import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SquareBoard<V> extends Board<Key, V> {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    void fillBoard(List<V> list) {
        for(int k = 0; k < this.height * this.height; k++){
            int i = k % this.weight;
            int j = k / this.weight;
            Key key = new Key(i, j);
            if (!list.isEmpty()){
                V value = list.get(k);
                addItem(key, value);
            } else {
                addItem(key, null);
            }
        }
    }

    @Override
    List<Key> availableSpace() {
        List<Key> nullKeys = new ArrayList<>();
        for(Map.Entry<Key, V> entry: board.entrySet()){
            V value = entry.getValue();
            Key key = entry.getKey();
            if(value == null) nullKeys.add(key);
        }
        return nullKeys;
    }

    @Override
    void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    V getValue(Key key) {
        return board.get(key);
    }

    @Override
    Key getKey(int i, int j) {
        for(Map.Entry<Key, V> entry: board.entrySet()){
            Key key = entry.getKey();
            if(key.getI() == i && key.getJ() == j) return key;
        }
        return null;
    }

    @Override
    List<Key> getColumn(int i) {
        List<Key> column = new ArrayList<>();
        for(int j = 0; j < height; j++){
            column.add(new Key(i, j));
        }
        return column;
    }

    @Override
    List<Key> getRow(int j) {
        List<Key> row = new ArrayList<>();
        for(int i = 0; i < weight; i++){
            row.add(new Key(i, j));
        }
        return row;
    }

    @Override
    boolean hasValue(V value) {
        for(V currentValue: board.values()){
            if(value == currentValue) return true;
        }
        return false;
    }

    @Override
    List<V> getValues(List<Key> keys) {
        List<V> values = new ArrayList<>();
        for(Key key: keys){
            values.add(getValue(key));
        }
        return values;
    }

}
