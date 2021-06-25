import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    List<Integer> moveAndMergeEqual(List<Integer> list){
        List<Integer> changedList = new ArrayList<>();
        boolean isPrevValueDoubled = false;
        for(Integer value: list){
            if(value != null){
                if(changedList.size() == 0) {
                    changedList.add(value);
                    continue;
                };
                int lastIndexChangedList = changedList.size() - 1;
                Integer lastElementChangedList = changedList.get(lastIndexChangedList);
                if(value.equals(lastElementChangedList) && !isPrevValueDoubled){
                    changedList.remove(lastElementChangedList);
                    changedList.add(value * 2);
                    isPrevValueDoubled = true;
                } else {
                    changedList.add(value);
                    isPrevValueDoubled = false;
                }
            }
        }
        while(changedList.size() != list.size()){
            changedList.add(null);
        }
        return changedList;
    };
}