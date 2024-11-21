public class Mathing implements math{
    private int one;
    private int two;
    public Mathing(int a, int b){
        this.one=a;
        this.two=b;
    }
    public int getA(){
        return one;
    }
    public int getB(){
        return two;
    }
    @Override
    public int summ(int a, int b){
        a=this.one;
        b=this.two;
        return a+b;
    }
}
