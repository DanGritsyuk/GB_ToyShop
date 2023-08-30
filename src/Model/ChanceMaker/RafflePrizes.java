package Model.ChanceMaker;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RafflePrizes<T extends LotteryItem> implements ChanceMaker<T>{
    private List<T> prizes;

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
    public void deleteLotteryItem(T item) {
        prizes.remove(item);
    }

    @Override
    public T getPrize(){
        int totalChance = 0;
        for (T prize : this.prizes) {
            totalChance += prize.getChance();
        }

        if(totalChance > 0) {
            Random rand = new Random();
            int randomNum = rand.nextInt(totalChance);

            int currentChance = 0;
            for (T prize : this.prizes) {
                currentChance += prize.getChance();
                if (randomNum < currentChance) {
                    return prize;
                }
            }
        }
        return null;
    }
}