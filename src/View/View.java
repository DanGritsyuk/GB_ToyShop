package View;

import Model.Presenter;

import java.util.List;

public interface View {
    void setPresenter(Presenter presenter);
    void startDraw();
    void showInfo(String text);
    String askText(String title);
    int askNum(String title);
    void showItemsInfo(List<String> itemsInfo);
    boolean yesNoDialog(String question);
}
