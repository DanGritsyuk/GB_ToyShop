import Model.Presenter;
import Model.Services.ToyShopService;
import View.TextCommand.TextCommandUI;

public class Main {
    public static void main(String[] args) {
        var presenter = new Presenter(new ToyShopService(), new TextCommandUI());
        presenter.startup();
    }
}