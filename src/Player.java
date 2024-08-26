import java.lang.reflect.Array;
import java.util.ArrayList;
public class Player {
    private ArrayList<Card> hand;
    private int[] handValue = new int[3];
    private int money;
    private String name;
    public static int prevBet = 0;
    public Player(int m, String n){
        money = m;
        name = n;
    }
    public void deal(ArrayList<Card> c){
        hand = c;
    }
    public int getMoney(){
        return money;
    }
    public int[] getHandValue(){
        return handValue;
    }
    public void changeHandValue(int highCard){
        handValue[2] = highCard;
    }
    public void changeHandValue(int handType, int value){
        handValue[0] = handType;
        handValue[1] = value;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public String toString(){
        String cards = "";
        for(int i = 0; i < hand.size(); i ++){
            cards += hand.get(i).getCard() + "\n";
        }
        return cards;
    }
    public void raise(int n){
        money -= n;
        prevBet = n;
    }
    public void check(){
        prevBet = 0;
    }
    public void call(int n){
        money -= n;
    }
    public void fold(){
        while(hand.size() > 0){
            hand.remove(0);
        }
    }
    public void winRound(int n){
        money += n;
    }
    public String getName(){
        return name;
    }
}
