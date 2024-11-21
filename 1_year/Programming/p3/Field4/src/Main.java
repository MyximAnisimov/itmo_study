import Actor.Human;
import Actor.StateOfMind;
import Items.Case;
import Items.Magnet;
import Items.MoonRock;
import Items.Remote;
import FMap.FlowerCity;
import FMap.Map;
import FMap.Pavilion;
import FMap.Room;
import FMap.World;
import FMap.DayTime;
public class Main {
    public static void main(String[] args) {


        Case case_1 = new Case(10,10,10);
        Human obj_1 = new Human("Знайка",18);
        Human obj_2 = new Human("Грабитель",17);
        FlowerCity flowerCity = new FlowerCity(Map.FlowerCity.getPlace());
        Pavilion pavilion  = new Pavilion(Map.Pavilion.getPlace());
        Room room  = new Room(Map.Room.getPlace());

        Magnet magnet = new Magnet(100,1,1,100,123);
        MoonRock moonRock = new MoonRock(1,1,1,100,123);

        Remote remote = new Remote(23.2f,22.2f,11.1f,magnet,moonRock);

        World world = new World("Мир", new Human[]{obj_1,obj_2}); // Помещаем наших персонажей в мир
        System.out.println("Наши персонажи прибывают в Цветочный Город !");


        System.out.println("В цветочном городе сейчас проходит: " + flowerCity.getDayTime() );
        world.movePeople(flowerCity,obj_1);
        world.movePeople(flowerCity,obj_2); // Теперь как и в сказке наши персонажи в Цветочном городе

        System.out.println(obj_1.getName() + " решает сходить в павильон пока светло");

        flowerCity.movePeople(pavilion,obj_1);
        System.out.println("Сегодня " + obj_1.getName() + " " +  obj_1.getEmotion()  + " ничего в теории не сможет испортить ему настроение");

        world.setDayTime(DayTime.NIGHT.getTime());
        System.out.println("Во всем городе наступает " + flowerCity.getDayTime());

        System.out.println(obj_2.getName() + " использует данную ситуацию , чтобы пробраться в Кабину");

        flowerCity.movePeople(room,obj_2);

        System.out.println("У Знайки наитие , что что-то не хорошо  , поэтому он решается отправиться в Кабину");

        pavilion.movePeople(room,obj_1);

        System.out.println("У ЗНАЙКИ ШОКККК");

        obj_1.ChangeEmotion(obj_1,StateOfMind.ANGRY.getMind());

        System.out.println("Знайка " + obj_1.getEmotion());

        System.out.println("Пора Знайке предпринять меры , Знайка решается воспользоваться чудо пультом , чтобы отключить Гравитацию и сделать так чтобы Грабитель подлетел");


        System.out.println("Знайка: " + obj_1.getLevitation()   + "; Грабитель: " + obj_2.getLevitation());

        remote.changeGravity(obj_1,obj_2,true);


        System.out.println("Знайка: " + obj_1.getLevitation()   + "; Грабитель: " + obj_2.getLevitation());

        remote.changeGravity(obj_1,obj_2,false);

        System.out.println("Знайка: " + obj_1.getLevitation()   + "; Грабитель: " + obj_2.getLevitation());


        System.out.println("Примем к сведению , что Знайка пожалел Грабителя и спустил его на землю");

        obj_1.ChangeEmotion(obj_1,StateOfMind.HAPPY.getMind());

        System.out.println("Знайка и Грабитель помирились");

        System.out.println("Для безопасности Знайка помещает свой пульт в защищенный футляр");

        case_1.WellConnected(remote);


        System.out.println("<<<<КОНЕЦ>>>>");




























































//        case_1.WellConnected(remote); case






























//        System.out.println(flowerCity.getPeople());
//        flowerCity.movePeople(pavilion,obj_1);
//        System.out.println(pavilion.getPeople());






// Реализация перемещения
//        System.out.println("Локация: "+ obj_1.getCurrent_Location() + " , Персонаж: " + obj_1.getName() );
//        System.out.println("Локация: "+ obj_2.getCurrent_Location() + " , Персонаж: " + obj_2.getName() );
//        System.out.println("<><><>");
//        System.out.println("кол-во людей в локации: " +Map.FlowerCity.getPlace()+ ": "+FlowerCity.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Pavilion.getPlace()+ ": "+Pavilion.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Room.getPlace()+ ": "+Room.getAmountofPeople());
//
//        System.out.println("<><><>");
//        obj_1.Change_Direction(Map.Pavilion.getPlace());
//        System.out.println("Локация: "+ obj_1.getCurrent_Location() + " , Персонаж: " + obj_1.getName() );
//        System.out.println("Локация: "+ obj_2.getCurrent_Location() + " , Персонаж: " + obj_2.getName() );
//        System.out.println("<><><>");
//        System.out.println("кол-во людей в локации: " +Map.FlowerCity.getPlace()+ ": "+FlowerCity.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Pavilion.getPlace()+ ": "+Pavilion.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Room.getPlace()+ ": "+Room.getAmountofPeople());
//
//        obj_2.Change_Direction(Map.Pavilion.getPlace());
//        System.out.println("кол-во людей в локации: " +Map.FlowerCity.getPlace()+ ": "+FlowerCity.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Pavilion.getPlace()+ ": "+Pavilion.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Room.getPlace()+ ": "+Room.getAmountofPeople());
//
//        obj_2.Change_Direction(Map.Room.getPlace());
//        System.out.println("кол-во людей в локации: " +Map.FlowerCity.getPlace()+ ": "+FlowerCity.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Pavilion.getPlace()+ ": "+Pavilion.getAmountofPeople());
//        System.out.println("кол-во людей в локации: " +Map.Room.getPlace()+ ": "+Room.getAmountofPeople());
//        obj_1.Change_Direction(Map.Room.getPlace());
//
//        System.out.println(Room.getAmountofPeople());
//
//        System.out.println("ДВА ЧЕЛОВЕКА В КОМНАТЕ ШОККККККККК");



















// Реализация пульта
//        System.out.println(obj_1.getLevitation());
//        remote.changeGravity(obj_1,true);
//        System.out.println(obj_1.getLevitation());
//        remote.changeGravity(obj_1,false);
//        System.out.println(obj_1.getLevitation());
//
//        System.out.println("<><><>");
//        System.out.println(obj_2.getCurrent_Location());
//        System.out.println("Объект 1 : "+obj_1.getLevitation());
//        System.out.println("Объект 2 : "+obj_2.getLevitation());
//        remote.changeGravity(obj_2,true);
//        System.out.println("Включаю пульт гравитации , который действует на оппонента 2");
//        System.out.println("Объект 1 : "+obj_1.getLevitation());
//        System.out.println("Объект 2 : "+obj_2.getLevitation());
































//        Agernator ageinv = new Agernator();
//        System.out.println(obj_1.getAge());
//        System.out.println("<><><>");
//        ageinv.change_age(obj_1,15);
//        System.out.println(obj_1.getAge());


    }
}