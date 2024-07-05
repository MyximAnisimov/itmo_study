package commands;

import managers.CollectionManager;

public class Info implements Command{
    private CollectionManager ComMan;
    public Info(CollectionManager ComMan){
        this.ComMan=ComMan;
    }
    @Override
    public String descr(){
        return "Info - вывести информацию о коллекции";
    }
    @Override
    public void execute(){
        System.out.println("Тип коллекции: "+ComMan.getType());
        Object[] array = ComMan.getElements();
        System.out.println("Количество элементов внутри коллекции: "+array.length);
    }


}
