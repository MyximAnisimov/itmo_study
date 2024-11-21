package commands;
import managers.CollectionManager;
public class Head implements Command {
private final CollectionManager comMan;
public Head(CollectionManager comMan){
    this.comMan=comMan;
}
@Override
public String descr(){
    return "head - вывод первого элемента на экран";
}
 @Override
    public void execute(){
System.out.println(comMan.getFirst());
 }
}
