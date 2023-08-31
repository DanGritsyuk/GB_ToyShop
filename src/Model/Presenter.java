package Model;
import View.View;

public class Presenter {
    private final ShopService shop;
    private final View view;

    public Presenter(ShopService shop, View view){
        this.shop = shop;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void startup(){
        shop.load();
        view.startDraw();
    }

    public void runCommand(String command){
        if ((command.equals("exit"))
                && (this.view.yesNoDialog("Сохранить изменения?"))){
            shop.save();
        }
        if (command.equals("list") || command.equals("l")){
            var info = this.shop.getItemsInfo();
            if (info.size() > 0){ view.showItemsInfo(info); }
            else { this.view.showInfo("В магазине нет товаров."); }
        }
        if (command.equals("add") || command.equals("a")){
            var name = view.askText("название игрушки");
            var count = view.askNum("количество");
            var chance = view.askNum("шанс выпадения");
            try{
                this.shop.addItem(name, Math.abs(count), Math.abs(chance));
                this.view.showInfo("Игрушка добавлена.");
            } catch (Exception ex){
                this.view.showInfo(ex.getMessage());
            }
        }
        if (command.equals("edit") || command.equals("e")){
            var id = view.askNum("идентификатор");
            var name = view.askText("новое название");
            var count = view.askNum("новое количество");
            var chance = view.askNum("новый шанс выпадения");
            try{
                this.shop.updateItem(id, name, Math.abs(count), Math.abs(chance));
                this.view.showInfo("данные " + name + " изменены.");
            }
            catch (Exception ex){
                this.view.showInfo(ex.getMessage());
            }
        }
        if (command.equals("delete") || command.equals("d")){
            var id = view.askNum("идентификатор");

            try{
                this.shop.deleteItem(id);
                this.view.showInfo("товар с кодом " + id + " удален");
            }
            catch (Exception ex){
                this.view.showInfo(ex.getMessage());
            }
        }
        if (command.equals("prize") || command.equals("p")){
            view.showInfo(this.shop.getPrize());
        }
    }

    public String getShopTitle(){
        return shop.getShopName();
    }
}