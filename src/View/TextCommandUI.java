package View;

import Model.Presenter;

public class TextCommandUI implements View {
    private Presenter presenter;

    public TextCommandUI(){
        this.presenter = new Presenter(this);
    }
    public void startup(){
        System.out.println("Shop open");
    }
}
