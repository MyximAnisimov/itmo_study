public final class Maxim extends Person {
    private String name;
    private int age;
    private int heigth;
    private int Array [];
    public Maxim(String name, int age, int heigth){
        this.name=name;
        this.age=age;
        this.heigth=heigth;
    }
    public Maxim(int Array[],int Num_elements) throws IndexOutOfBoundsException{
        class OutArray extends IndexOutOfBoundsException{
            public OutArray(String message){
                super(message);
            }
        }
        for(int i=0;i<Num_elements;i++){
            if(Array.length<Num_elements){
                throw new OutArray("Вы превысили границы массива");
        }
    }}
    public int Dividing(int Delimoe, int Delitel) throws ArithmeticException{
        class DelenieNaNol extends ArithmeticException{
            public DelenieNaNol(String message){
                super(message);
            }
        }
        if(Delitel==0){
            throw new DelenieNaNol("Ты делишь на ноль");
        }
        return Delimoe/Delitel;
    }
    @Override
    public String toString(){
        return "Class Maxim: { name= "+name+
                " age= "+age+" heigth= "+heigth+" }";
    }
    public void set(String name){
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }
public void ravno(Maxim o) throws Exception{
        class NotObject extends Exception{
            public NotObject(String message){
                super(message);
            }
        }
        if(this.age !=o.age){
            throw new NotObject(" Объекты отличаются");
        }
        else{
            System.out.println("true");
        }}
    private void summer(int a){
        System.out.println(a+a+a+a);
    }
}
