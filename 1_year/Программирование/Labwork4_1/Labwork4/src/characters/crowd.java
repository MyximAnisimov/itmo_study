package characters;

public class crowd {
    private int id;
    private static int Num_people;
    public crowd(int id){
     this.id=id;
this.Num_people=Num_people++;
   }
public static int getNum_people(){
        return Num_people;
}
    @Override
    public int hashCode(){
        return id;
    }
    @Override
    public String toString(){
        return "Crowd{" +
                "Number of people= "+Num_people+ '}';}
}

