package Model.ChanceMaker;

import java.util.List;

public interface ChanceMaker<LotteryItem> {
    void setStoreItems(List<LotteryItem> items);

    LotteryItem getPrize();
}
