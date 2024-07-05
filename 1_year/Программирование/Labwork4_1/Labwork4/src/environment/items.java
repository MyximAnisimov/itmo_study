package environment;

public class items {
    public class Scafandr{
        private boolean IsScafandrOn;
        private String name;

        public void setScafandrOn(boolean scafandrOn) {
            IsScafandrOn = scafandrOn;
        }
public boolean checkScafandr(){
            return IsScafandrOn;
}
        public Scafandr(String name, boolean IsScafandrOn){
            this.name=name;
            this.IsScafandrOn=IsScafandrOn;

        }
        public String getName(){
            return name;
        }
        @Override
        public String toString(){
            return "Scafandr{" +
                    "name= "+name+ '}';}
    }
}
