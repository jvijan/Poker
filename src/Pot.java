public class Pot {
    private int inPot;
    public Pot(){
        inPot = 0;
    }
    public void add(int n){
        inPot += n;
    }
    public void resetPot(){
        inPot = 0;
    }
    public void takeOut(int n){
        inPot -= n;
    }
    public int getInPot(){
        return inPot;
    }
}
