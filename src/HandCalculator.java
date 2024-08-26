import java.util.ArrayList;
public class HandCalculator {
    private ArrayList<Card> combined = new ArrayList<>();
    private ArrayList<Card> combinedAce = new ArrayList<>();
    public HandCalculator(ArrayList<Card> cen, ArrayList<Card> hand){
        for(int i = 0; i < cen.size(); i++){
            combined.add(cen.get(i));
            combinedAce.add(cen.get(i));
        }
        for(int j = 0; j < hand.size(); j++){
            combined.add(hand.get(j));
            combinedAce.add(cen.get(j));
        }
        for(int k = 1; k < combined.size(); k ++){
            int val = combined.get(k).getValue();
            Card c = combined.get(k);
            int l = k-1;
            while(l >= 0 && combined.get(l).getValue() > val){
                combined.set(l+1, combined.get(l));
                l--;
            }
            combined.set(l+1,c);
        }
        for(int k = 0; k < combinedAce.size(); k++){
            if(combinedAce.get(k).getValue() == 14){
                combinedAce.get(k).aceLow();
                combinedAce.add(0,combinedAce.remove(k));
            }
        }
    }
    public void isPair(Player p){
        int max = combined.get(0).getValue();
        boolean pair = false;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 2 && combined.get(i).getValue() >= max){
                max = combined.get(i).getValue();
                pair = true;
            }
        }
        if(pair){
            p.changeHandValue(1, max);
        }
    }
    public void isTwoPair(Player p){
        int max = combined.get(0).getValue();
        int pairCount = 0;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 2 && combined.get(i).getValue() >= max){
                max = combined.get(i).getValue();
                pairCount ++;
            }
        }
        if(pairCount == 2){
            p.changeHandValue(2, max);
        }
    }
    public void isThreeOfAKind(Player p){
        int max = combined.get(0).getValue();
        boolean threeOfAKind = false;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 3 && combined.get(i).getValue() >= max){
                max = combined.get(i).getValue();
                threeOfAKind = true;
            }
        }
        if(threeOfAKind) {
            p.changeHandValue(3, max);
        }
    }
    public void isStraight(Player p){
        int straightCount = 1;
        int straightCountAce = 1;
        int prevVal = combinedAce.get(0).getValue();
        int max = prevVal;
        for(int j = 1; j < combinedAce.size(); j++){
            if(prevVal + 1 == combinedAce.get(j).getValue()){
                straightCountAce ++;
                if(straightCountAce >= 5){
                    max = combinedAce.get(j).getValue();
                }
            }
            prevVal = combinedAce.get(j).getValue();
        }
        prevVal = combined.get(0).getValue();
        for(int j = 1; j < combined.size(); j++){
            if(prevVal + 1 == combined.get(j).getValue()){
                straightCount ++;
                if(straightCount >= 5){
                    max = combined.get(j).getValue();
                }
            }
            prevVal = combined.get(j).getValue();
        }
        if(straightCount >= 5 || straightCountAce >= 5){
            p.changeHandValue(4, max);
        }
    }
    public void isFlush(Player p){
        int heartCount = 0;
        int diamondCount = 0;
        int clubCount = 0;
        int spadeCount = 0;
        int max = 0;
        for(int i = 0; i < combined.size(); i ++){
            if(combined.get(i).getSuit().equals("heart")){
                heartCount++;
            }
            if(combined.get(i).getSuit().equals("diamond")){
                diamondCount++;
            }
            if(combined.get(i).getSuit().equals("club")){
                clubCount++;
            }
            if(combined.get(i).getSuit().equals("spade")){
                spadeCount++;
            }
            if(heartCount >= 5 || diamondCount >= 5 || clubCount >= 5 || spadeCount >= 5){
                max = combined.get(i).getValue();
            }
        }
        if(heartCount >= 5 || diamondCount >= 5 || clubCount >= 5 || spadeCount >= 5){
            p.changeHandValue(5, max);
        }
    }
    public void isFullHouse(Player p){
        int tripleMax = combined.get(0).getValue();
        boolean pair = false;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 2){
                pair = true;
            }
        }
        boolean threeOfAKind = false;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 3 && combined.get(i).getValue() >= tripleMax){
                tripleMax = combined.get(i).getValue();
                threeOfAKind = true;
            }
        }
        if(threeOfAKind && pair){
            p.changeHandValue(6,tripleMax);
        }
    }
    public void isFourOfAKind(Player p){
        int max = combined.get(0).getValue();
        boolean fourOfAKind = false;
        for(int i = 0; i < combined.size() - 1; i ++){
            int count = 1;
            for(int j = i+1; j < combined.size(); j ++){
                if(combined.get(i).getValue() == combined.get(j).getValue()){
                    count++;
                }
            }
            if(count == 4 && combined.get(i).getValue() >= max){
                max = combined.get(i).getValue();
                fourOfAKind = true;
            }
        }
        if(fourOfAKind) {
            p.changeHandValue(7, max);
        }
    }
    public void isStraightFlush(Player p){
        int straightCount = 1;
        int straightCountAce = 1;
        int flushCountAce = 1;
        int prevVal = combinedAce.get(0).getValue();
        int max = prevVal;
        int flushCount = 1;
        for(int j = 1; j < combined.size(); j++){
            if(prevVal + 1 == combined.get(j).getValue() && combined.get(j-1).getSuit().equals(combined.get(j).getSuit())){
                straightCountAce ++;
                flushCountAce++;
                if(straightCountAce >= 5){
                    max = combined.get(j).getValue();
                }
            }
            prevVal = combined.get(j).getValue();
        }
        for(int j = 1; j < combined.size(); j++){
            if(prevVal + 1 == combined.get(j).getValue() && combined.get(j-1).getSuit().equals(combined.get(j).getSuit())){
                straightCount ++;
                flushCount++;
                if(straightCount >= 5){
                    max = combined.get(j).getValue();
                }
            }
            prevVal = combined.get(j).getValue();
        }
        if((straightCount >= 5 && flushCount >= 5) || (straightCountAce >= 5 && flushCountAce >= 5)){
            p.changeHandValue(8, max);
        }
    }
    public void isRoyalFlush(Player p){
        int straightCount = 1;
        int prevVal = combined.get(0).getValue();
        int max = prevVal;
        int flushCount = 1;
        int faceCardTrack = 0;
        for(int j = 1; j < combined.size(); j++){
            if(prevVal + 1 == combined.get(j).getValue() && combined.get(j-1).getSuit().equals(combined.get(j).getSuit())){
                straightCount ++;
                flushCount++;
                if(straightCount >= 5){
                    max = combined.get(j).getValue();
                }
            }
            prevVal = combined.get(j).getValue();
        }
        for(int i = 0; i < combined.size(); i ++){
            if(combined.get(i).getValue() >= 10){
                faceCardTrack++;
            }
        }
        if(faceCardTrack >= 5 && flushCount >= 5 && straightCount >= 5){
            p.changeHandValue(9, max);
        }
    }
    public void highCard(Player p){
        int max = p.getHand().get(0).getValue();
        for(int i = 0; i < p.getHand().size(); i ++){
            if(p.getHand().get(i).getValue() > max && p.getHandValue()[i] != p.getHand().get(i).getValue()){
                max = p.getHand().get(i).getValue();
            }
        }
        p.changeHandValue(max);
    }
    public void handValue(Player p){
        isPair(p);
        isTwoPair(p);
        isThreeOfAKind(p);
        isStraight(p);
        isFlush(p);
        isFullHouse(p);
        isFourOfAKind(p);
        isStraightFlush(p);
        isRoyalFlush(p);
        highCard(p);
    }
}
