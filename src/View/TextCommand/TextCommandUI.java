package View.TextCommand;

import Model.Presenter;
import View.View;

import java.util.List;

public class TextCommandUI implements View {

    private final int LINE_CHARACTERS_COUNT = 190;
    private final String GREEN_ON = "\u001B[32m";
    private final String GREEN_OFF = "\u001B[0m";
    private Presenter presenter;
    private ConsoleManager cmd;

    public TextCommandUI(){
        this.cmd = new ConsoleManager("cp866");
    }
    @Override
    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void startDraw() {

        var runApp = true;
        while (runApp) {
            drawHeader(presenter.getShopTitle().toUpperCase());
            drawCommandText();
            var command = getCommand("Введите команду: ").toLowerCase();
            if (command.equals("exit")){ runApp = false; }
            this.presenter.runCommand(command);

            this.cmd.printText("\n".repeat(20));
        }
        cmd.printText("Программа закрыта.");
    }

    @Override
    public void showInfo(String text){
        cmd.printText(text);
        cmd.inputText("Чтобы продолжить нажмите Enter...");
    }

    @Override
    public void showItemsInfo(List<String> itemsInfo) {
        cmd.printText("\nТОВАРОВ В НАЛИЧИИ:");
        for(var info : itemsInfo){
            cmd.printText(info);
        }
        cmd.inputText("Нажмите Enter...");
    }

    @Override
    public String askText(String title) {
        return getCommand("Введите " + title + ": ");
    }

    @Override
    public int askNum(String title) {
        var numText = getCommand("Введите " + title + ": ");
        try {
            return Integer.parseInt(numText);
        } catch (NumberFormatException e) {
            return askNum("Некорректный ввод. Попробуйте еще раз: ");
        }
    }

    @Override
    public boolean yesNoDialog(String question) {
        var answer = getCommand(question + "\nВведите [" +
                GREEN_ON + "y" + GREEN_OFF + "\\" + GREEN_ON + "n" + GREEN_OFF + "]: ");
        return answer.equals("y");
    }

    private void drawHeader(String headerText){
        cmd.consoleClear();
        cmd.printText("\n\n" + headerText + "\n" + "=".repeat(LINE_CHARACTERS_COUNT), "\n\n");
    }

    private void drawCommandText(){
        var text =
                "Показать все игрушки:   " + GREEN_ON + "l" + GREEN_OFF + "ist\n" +
                "Добавить новую игрушку: " + GREEN_ON + " a" + GREEN_OFF + "dd\n" +
                "Редактировать игрушку:  " + GREEN_ON + "e" + GREEN_OFF + "dit\n" +
                "Удалить игрушку:      " + GREEN_ON + "d" + GREEN_OFF + "elete\n" +
                "Розыгрыш игрушки:      " + GREEN_ON + "p" + GREEN_OFF + "rize\n" +
                "Выход: " + GREEN_ON + "exit" + GREEN_OFF + "\n" +
                "----------------------------\n";
        cmd.printText(text);
    }

    private String getCommand(String message){
        String command = cmd.inputText(message);
        if (command.isEmpty()){
            clearLine();
            command = getCommand("Пустое поле не допустимо, повторите попытку: ");
        }
        return command;
    }

    private void clearLine(){
        cmd.printText("\033[A", "");
        cmd.printText(" ".repeat(LINE_CHARACTERS_COUNT), "");
        cmd.printText("\b".repeat(LINE_CHARACTERS_COUNT), "");
    }
}
