package Model.ChanceMaker;

import java.util.List;

public interface ChanceMaker {
    void setStoreItems(List<LotteryItem> items);

    LotteryItem getPrize();
}
