package Model.ChanceMaker;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class RafflePrizes<T extends LotteryItem> implements ChanceMaker<T>{
    private final List<T> lotList;
    private final Queue<T> prizesQueue;

    public RafflePrizes(){
        this(new LinkedList<>());
    }

    public RafflePrizes(List<T> lotList){
        this.lotList = lotList;
        this.prizesQueue = new LinkedList<>();
    }

    @Override
    public void setLotteryItem(T item){
        if (!this.lotList.contains(item)){
            lotList.add(item);
        }
    }

    @Override
    public void deleteLotteryItem(T item) { lotList.remove(item); }

    @Override
    public boolean startPrizeDraw() {
        int count = 0;
        for(var lot : this.lotList){
            count += calculateChance(lot.getChance());
        }

        Random random = new Random();
        for (int randomNumsCount = 0; randomNumsCount < 10; randomNumsCount++) {

            int index = random.nextInt(count);
            for (var lot : this.lotList){
                index -= calculateChance(lot.getChance());
                if(index < 0) {
                    synchronized (prizesQueue) {
                        prizesQueue.offer(lot);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T getPrize(){
        T prize = null;
        synchronized (prizesQueue) {
            if (!prizesQueue.isEmpty()) {
                 prize = prizesQueue.poll();
            }
        }
        return prize;
    }

    private int calculateChance(int chance){
        return Math.min(chance, 100);
    }
}