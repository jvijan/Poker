import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> dealt = new ArrayList<>();
    private Card AceHeart = new Card(14, "hearts");
    private Card TwoHeart = new Card(2, "hearts");
    private Card ThreeHeart = new Card(3, "hearts");
    private Card FourHeart = new Card(4, "hearts");
    private Card FiveHeart = new Card(5, "hearts");
    private Card SixHeart = new Card(6, "hearts");
    private Card SevenHeart = new Card(7, "hearts");
    private Card EightHeart = new Card(8, "hearts");
    private Card NineHeart = new Card(9, "hearts");
    private Card TenHeart = new Card(10, "hearts");
    private Card JackHeart = new Card(11, "hearts");
    private Card QueenHeart = new Card(12, "hearts");
    private Card KingHeart = new Card(13, "hearts");
    private Card AceDiamond = new Card(14, "diamonds");
    private Card TwoDiamond = new Card(2, "diamonds");
    private Card ThreeDiamond = new Card(3, "diamonds");
    private Card FourDiamond = new Card(4, "diamonds");
    private Card FiveDiamond = new Card(5, "diamonds");
    private Card SixDiamond = new Card(6, "diamonds");
    private Card SevenDiamond = new Card(7, "diamonds");
    private Card EightDiamond = new Card(8, "diamonds");
    private Card NineDiamond = new Card(9, "diamonds");
    private Card TenDiamond = new Card(10, "diamonds");
    private Card JackDiamond = new Card(11, "diamonds");
    private Card QueenDiamond = new Card(12, "diamonds");
    private Card KingDiamond = new Card(13, "diamonds");
    private Card AceSpade = new Card(14, "spades");
    private Card TwoSpade = new Card(2, "spades");
    private Card ThreeSpade = new Card(3, "spades");
    private Card FourSpade = new Card(4, "spades");
    private Card FiveSpade = new Card(5, "spades");
    private Card SixSpade = new Card(6, "spades");
    private Card SevenSpade = new Card(7, "spades");
    private Card EightSpade = new Card(8, "spades");
    private Card NineSpade = new Card(9, "spades");
    private Card TenSpade = new Card(10, "spades");
    private Card JackSpade = new Card(11, "spades");
    private Card QueenSpade = new Card(12, "spades");
    private Card KingSpade = new Card(13, "spades");
    private Card AceClub = new Card(14, "clubs");
    private Card TwoClub = new Card(2, "clubs");
    private Card ThreeClub = new Card(3, "clubs");
    private Card FourClub = new Card(4, "clubs");
    private Card FiveClub = new Card(5, "clubs");
    private Card SixClub = new Card(6, "clubs");
    private Card SevenClub = new Card(7, "clubs");
    private Card EightClub = new Card(8, "clubs");
    private Card NineClub = new Card(9, "clubs");
    private Card TenClub = new Card(10, "clubs");
    private Card JackClub = new Card(11, "clubs");
    private Card QueenClub = new Card(12, "clubs");
    private Card KingClub = new Card(13, "clubs");
    
    public Deck(){
        deck.add(AceHeart);
        deck.add(TwoHeart);
        deck.add(ThreeHeart);
        deck.add(FourHeart);
        deck.add(FiveHeart);
        deck.add(SixHeart);
        deck.add(SevenHeart);
        deck.add(EightHeart);
        deck.add(NineHeart);
        deck.add(TenHeart);
        deck.add(JackHeart);
        deck.add(QueenHeart);
        deck.add(KingHeart);
        deck.add(AceDiamond);
        deck.add(TwoDiamond);
        deck.add(ThreeDiamond);
        deck.add(FourDiamond);
        deck.add(FiveDiamond);
        deck.add(SixDiamond);
        deck.add(SevenDiamond);
        deck.add(EightDiamond);
        deck.add(NineDiamond);
        deck.add(TenDiamond);
        deck.add(JackDiamond);
        deck.add(QueenDiamond);
        deck.add(KingDiamond);
        deck.add(AceSpade);
        deck.add(TwoSpade);
        deck.add(ThreeSpade);
        deck.add(FourSpade);
        deck.add(FiveSpade);
        deck.add(SixSpade);
        deck.add(SevenSpade);
        deck.add(EightSpade);
        deck.add(NineSpade);
        deck.add(TenSpade);
        deck.add(JackSpade);
        deck.add(QueenSpade);
        deck.add(KingSpade);
        deck.add(AceClub);
        deck.add(TwoClub);
        deck.add(ThreeClub);
        deck.add(FourClub);
        deck.add(FiveClub);
        deck.add(SixClub);
        deck.add(SevenClub);
        deck.add(EightClub);
        deck.add(NineClub);
        deck.add(TenClub);
        deck.add(JackClub);
        deck.add(QueenClub);
        deck.add(KingClub);
    }
    public void resetDeck(){
        while(dealt.size() > 0){
            deck.add(dealt.remove(0));
        }
        Collections.shuffle(deck);
    }
    public ArrayList<Card> dealPlayer(){
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = 0; i < 2; i ++){
            Card c = deck.remove(0);
            hand.add(c);
            dealt.add(c);
        }
        return hand;
    }
    public ArrayList<Card> dealCenter(int r){
        ArrayList<Card> center = new ArrayList<>();
        if(r == 0){
            for(int i = 0; i < 3; i ++){
                Card c = deck.remove(0);
                center.add(c);
                dealt.add(c);
            }
        }
        if(r == 1 || r == 2){
            Card c = deck.remove(0);
            center.add(c);
            dealt.add(c);
        }
        return center;
    }

}
