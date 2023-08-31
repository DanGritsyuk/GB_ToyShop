package Model.ChanceMaker;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RafflePrizes<T extends LotteryItem> implements ChanceMaker<T>{
    private final List<T> prizes;

    public RafflePrizes(){
        this(new LinkedList<>());
    }

    public RafflePrizes(List<T> prizes){
        this.prizes = prizes;
    }

    @Override
    public void setLotteryItem(T item){
        if (!this.prizes.contains(item)){
            prizes.add(item);
        }
    }

    @Override
    public void deleteLotteryItem(T item) { prizes.remove(item); }

    @Override
    public T getPrize(){
        int count = 0;
        for(var prize : this.prizes){
            count += calculateChance(prize.getChance());
        }

        Random random = new Random();
        for (int randomNumsCount = 0; randomNumsCount < 10; randomNumsCount++) {

            int index = random.nextInt(count);
            for (var prize : this.prizes){
                index -= calculateChance(prize.getChance());
                if(index < 0) {
                    return prize;
                }
            }
        }
        return null;
    }

    private int calculateChance(int chance){
        return Math.min(chance, 100);
    }
}