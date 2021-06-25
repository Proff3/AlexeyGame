import java.util.*;

public class Game2048 implements Game {

    public static  final int GAME_SIZE = 4;

    GameHelper helper = new GameHelper();
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    Random random = new Random();

    @Override
    public void init() {
        board.board = new HashMap<>();
        board.fillBoard(new ArrayList<>());
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() {
        List<Integer> currentRowValues = getCurrentValuesByRow();
        List<Integer> currentColumnValues = getCurrentValuesByColumn();
        List<Integer> moveAndMergedRowValues = getMoveAndMergedRowValues();
        List<Integer> moveAndMergedColumnValues = getMoveAndMergedColumnValues();
        List<Key> availableSpace = board.availableSpace();
        if (!currentColumnValues.equals(moveAndMergedColumnValues)) return true;
        if (!currentRowValues.equals(moveAndMergedRowValues)) return true;
        return !availableSpace.isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            List<Integer> currentRowValues = getCurrentValuesByRow();
            List<Integer> moveAndMergedRowValues = getMoveAndMergedRowValues(direction);
            if (currentRowValues.equals(moveAndMergedRowValues)) return true;
            board.fillBoard(moveAndMergedRowValues);
        } else {
            List<Integer> currentColumnValues = getCurrentValuesByColumn();
            List<Integer> moveAndMergedColumnValues = getMoveAndMergedColumnValues(direction);
            if (currentColumnValues.equals(moveAndMergedColumnValues)) return true;
            List<Integer> resultValues = new ArrayList<>();
            for(int i = 0; i < GAME_SIZE; i++){
                for(int j = 0; j < GAME_SIZE; j++){
                    int idx = j * GAME_SIZE + i;
                    Integer value = moveAndMergedColumnValues.get(idx);
                    resultValues.add(value);
                }
            }
            board.fillBoard(resultValues);
        }
        if (!board.availableSpace().isEmpty()) {
            addItem();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addItem() {
        int value = random.nextInt(5);
        value = value == 4 ? 4 : 2;
        List<Key> availableSpace = board.availableSpace();
        Key randomKey;
        do{
            int i = random.nextInt(GAME_SIZE);
            int j = random.nextInt(GAME_SIZE);
            randomKey = new Key(i, j);
        }while(!availableSpace.contains(randomKey));
        board.addItem(randomKey, value);
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.board.containsValue(2048);
    }

    List<Integer> getCurrentValuesByRow() {
        List<Integer> currentValuesByRow = new ArrayList<>();
        for(int i = 0; i < GAME_SIZE; i++){
            List<Key> keysOfRow = board.getRow(i);
            List<Integer> valuesOfRow = board.getValues(keysOfRow);
            currentValuesByRow.addAll(valuesOfRow);
        }
        return currentValuesByRow;
    }

    List<Integer> getCurrentValuesByColumn() {
        List<Integer> currentValuesByColumn = new ArrayList<>();
        for(int i = 0; i < GAME_SIZE; i++){
            List<Key> keysOfColumn = board.getColumn(i);
            List<Integer> valuesOfColumn = board.getValues(keysOfColumn);
            currentValuesByColumn.addAll(valuesOfColumn);
        }
        return currentValuesByColumn;
    }

    List<Integer> getMoveAndMergedRowValues() {
        List<Integer> resultValues = new ArrayList<>();
        for(int i = 0; i < GAME_SIZE; i++){
            List<Key> keysOfRow = board.getRow(i);
            List<Integer> valuesOfRow = board.getValues(keysOfRow);
            List<Integer> MovedAndMergedValuesOfRow = helper.moveAndMergeEqual(valuesOfRow);
            resultValues.addAll(MovedAndMergedValuesOfRow);
        }
        return resultValues;
    }

    List<Integer> getMoveAndMergedRowValues(Direction direction) {
        List<Integer> resultValues = new ArrayList<>();
        if (direction == Direction.LEFT){
            resultValues = getMoveAndMergedRowValues();
        } else {
            for(int i = 0; i < GAME_SIZE; i++){
                List<Key> keysOfRow = board.getRow(i);
                List<Integer> valuesOfRow = board.getValues(keysOfRow);
                Collections.reverse(valuesOfRow);
                List<Integer> movedAndMergedValuesOfRow = helper.moveAndMergeEqual(valuesOfRow);
                Collections.reverse(movedAndMergedValuesOfRow);
                resultValues.addAll(movedAndMergedValuesOfRow);
            }
        }
        return resultValues;
    }

    List<Integer> getMoveAndMergedColumnValues() {
        List<Integer> resultValues = new ArrayList<>();
        for(int i = 0; i < GAME_SIZE; i++){
            List<Key> keysOfColumn = board.getColumn(i);
            List<Integer> valuesOfColumn = board.getValues(keysOfColumn);
            List<Integer> movedAndMergedValuesOfColumn = helper.moveAndMergeEqual(valuesOfColumn);
            resultValues.addAll(movedAndMergedValuesOfColumn);
        }
        return resultValues;
    }

    List<Integer> getMoveAndMergedColumnValues(Direction direction) {
        List<Integer> resultValues = new ArrayList<>();
        if (direction == Direction.TOP){
            resultValues = getMoveAndMergedColumnValues();
        } else {
            for(int i = 0; i < GAME_SIZE; i++){
                List<Key> keysOfColumn = board.getColumn(i);
                List<Integer> valuesOfColumn = board.getValues(keysOfColumn);
                Collections.reverse(valuesOfColumn);
                List<Integer> movedAndMergedValuesOfColumn = helper.moveAndMergeEqual(valuesOfColumn);
                Collections.reverse(movedAndMergedValuesOfColumn);
                resultValues.addAll(movedAndMergedValuesOfColumn);
            }

        }
        return resultValues;
    }
}
