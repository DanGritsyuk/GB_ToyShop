package Model.ChanceMaker;

public interface ChanceMaker<LotteryItem> {
    void setLotteryItem(LotteryItem item);
    void deleteLotteryItem(LotteryItem item);
    boolean startPrizeDraw();
    LotteryItem getPrize();
}
