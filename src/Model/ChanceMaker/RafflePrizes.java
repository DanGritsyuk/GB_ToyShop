package Model.ChanceMaker;
import java.util.List;

public class RafflePrizes<T extends LotteryItem> implements ChanceMaker<T>{
    @Override
    public void setStoreItems(List<T> items){}
    public T getPrize(){
        return null;
    }
}