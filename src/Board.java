import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K, V> {
    int weight;
    int height;
    Map<K, V> board = new HashMap<>();
    public Board(int height, int weight){
        this.weight = weight;
        this.height = height;
    }
    abstract void fillBoard(List<V> list);
    abstract List<K> availableSpace();
    abstract void addItem(K key, V value);
    abstract V getValue(K key);
    abstract K getKey(int i, int j);
    abstract List<K> getColumn(int j);
    abstract List<K> getRow(int i);
    abstract boolean hasValue(V value);
    abstract List<V> getValues(List<K> keys);
}
