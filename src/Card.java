public class Card {
    private int value;
    private String suit;
    public Card(int v, String s){
        value = v;
        suit = s;
    }
    public int getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
    public void aceLow(){
        value = 1;
    }
    public String getCard(){
        if(value == 14){
            return "ace of " + suit;
        }
        if(value == 13){
            return "king of " + suit;
        }
        if(value == 12){
            return "queen of " + suit;
        }
        if(value == 11){
            return "jack of " + suit;
        }
        return value + " of " + suit;
    }
}
