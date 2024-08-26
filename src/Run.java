import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
public class Run {
    public static void main(String[] args) {
        Random ran = new Random();
        ArrayList<Player> p = new ArrayList<>();
        ArrayList<Player> originalP = new ArrayList<>();
        ArrayList<String> prevAction = new ArrayList<>();
        ArrayList<Integer> bets = new ArrayList<>();
        ArrayList<Card> center;
        HandCalculator handCalc;
        int personRaised = 0;
        boolean raised = false;
        int dealer = 0;
        String betType = "";
        int bet = 0;
        Pot pot = new Pot();
        Deck deck = new Deck();
        boolean endGame = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players?");
        int pl = sc.nextInt();
        for (int i = 0; i < pl; i++) {
            System.out.println("Player " + (i + 1) + " name:");
            String a = sc.next();
            originalP.add(new Player(20, a));
            prevAction.add("");
            bets.add(0);
        }
        while (!endGame) {
            deck.resetDeck();
            pot.resetPot();
            int tempDealer = dealer;
            int maxHandValue = 0;
            for (int i = 0; i < originalP.size(); i++) {
                p.add(originalP.get(i));
            } //for
            for (int j = 0; j < p.size(); j++) {
                p.get(j).raise(1);
                pot.add(1);
                ArrayList<Card> h1 = deck.dealPlayer();
                p.get(j).deal(h1);
            }//for
            Player.prevBet = 0;
            for (int k = tempDealer + 1; k < p.size(); k++) {
                System.out.println("See " + p.get(k).getName() + " cards? y/n");
                String ans = sc.next();
                while (!ans.toLowerCase().equals("y")) {
                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    ans = sc.next();
                }
                System.out.println(p.get(k));
                System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                        ". There is $" + pot.getInPot() + " in the pot.");
                betType = sc.next();
                while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                        betType.toLowerCase().equals("fold"))) {
                    System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot.");
                    betType = sc.next();
                }
                if (betType.toLowerCase().equals("raise")) {
                    System.out.println("How much?");
                    bet = sc.nextInt();
                    p.get(k).raise(bet);
                    pot.add(bet);
                    prevAction.set(k, "raise");
                    bets.set(k, bet);
                    raised = true;
                    personRaised = k;
                    if (p.get(k).getMoney() < 0) {
                        pot.takeOut(Math.abs((p.get(k).getMoney())));
                        p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                    }
                }
                if (betType.toLowerCase().equals("fold")) {
                    p.get(k).fold();
                    p.remove(k);
                    k--;
                    if (p.size() == 1) {
                        k = p.size() + 1;
                    }
                }
                if (betType.toLowerCase().equals("check")) {
                    p.get(k).check();
                }
                if (!betType.toLowerCase().equals("fold")) {
                    System.out.println("You now have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot.");
                }
                System.out.println("Continue to next player? y/n");
                ans = sc.next();
                while (!ans.toLowerCase().equals("y")) {
                    System.out.println("Continue to next player?");
                    ans = sc.next();
                }
                if (raised) {
                    k = p.size();
                }
                //Clear.clearScreen();
            }
            if (!raised && !(p.size() == 1)) {
                for (int k = 0; k <= tempDealer; k++) {

                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        ans = sc.next();
                    }
                    System.out.println(p.get(k));
                    System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot.");
                    betType = sc.next();
                    while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                            betType.toLowerCase().equals("fold"))) {
                        System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                        betType = sc.next();
                    }
                    if (betType.toLowerCase().equals("raise")) {
                        System.out.println("How much?");
                        bet = sc.nextInt();
                        p.get(k).raise(bet);
                        pot.add(bet);
                        prevAction.set(k, "raise");
                        bets.set(k, bet);
                        raised = true;
                        personRaised = k;
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (betType.toLowerCase().equals("fold")) {
                        p.get(k).fold();
                        p.remove(k);
                        k--;
                        tempDealer--;
                        if (p.size() == 1) {
                            k = p.size() + 1;
                        }
                    }
                    if (betType.toLowerCase().equals("check")) {
                        p.get(k).check();
                    }
                    if (!betType.toLowerCase().equals("fold")) {
                        System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                + pot.getInPot() + " in the pot.");
                    }
                    System.out.println("Continue to next player? y/n");
                    ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("Continue to next player?");
                        ans = sc.next();
                    }
                    if (raised) {
                        k = p.size();
                    }
                    //Clear.clearScreen();
                }
            }
            while (raised && !(p.size() == 1)) {
                for (int k = personRaised + 1; k < p.size(); k++) {
                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        ans = sc.next();
                    }
                    System.out.println(p.get(k));
                    System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot. " +
                            "The previous bet was $" + Player.prevBet);
                    betType = sc.next();
                    while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                            betType.toLowerCase().equals("fold"))) {
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                    }
                    if (betType.toLowerCase().equals("raise")) {
                        System.out.println("How much? You will pay the specified amount + $" + Player.prevBet);
                        bet = sc.nextInt();
                        pot.add((bet + Player.prevBet) - bets.get(k));
                        p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                        prevAction.set(k, "raise");
                        bets.set(k, (bet + Player.prevBet) - bets.get(k));
                        personRaised = k;
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (betType.toLowerCase().equals("fold")) {
                        p.get(k).fold();
                        p.remove(k);
                        if (k <= tempDealer) {
                            tempDealer--;
                        }
                        k--;
                        if (p.size() == 1) {
                            k = p.size() + 1;
                        }
                    }
                    if (betType.toLowerCase().equals("call")) {
                        p.get(k).call(Player.prevBet - bets.get(k));
                        pot.add(Player.prevBet - bets.get(k));
                        prevAction.set(k, "call");
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (!betType.toLowerCase().equals("fold")) {
                        System.out.println("You now have $" + p.get(k).getMoney() + ". There is $" +
                                pot.getInPot() + " in the pot.");
                    }
                    System.out.println("Continue to next player? y/n");
                    ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("Continue to next player?");
                        ans = sc.next();
                    }
                    //Clear.clearScreen();
                }
                for (int k = 0; k < personRaised; k++) {
                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        ans = sc.next();
                    }
                    System.out.println(p.get(k));
                    System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot. " +
                            "The previous bet was $" + Player.prevBet);
                    betType = sc.next();
                    while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                            betType.toLowerCase().equals("fold"))) {
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                    }
                    if (betType.toLowerCase().equals("raise")) {
                        System.out.println("How much? You will pay the specified amount + $" +
                                (Player.prevBet - bets.get(k)));
                        bet = sc.nextInt();
                        pot.add((bet + Player.prevBet) - bets.get(k));
                        p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                        prevAction.set(k, "raise");
                        bets.set(k, (bet + Player.prevBet) - bets.get(k));
                        personRaised = k;
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (betType.toLowerCase().equals("fold")) {
                        p.get(k).fold();
                        p.remove(k);
                        prevAction.set(k, "fold");
                        if (k <= tempDealer) {
                            tempDealer--;
                        }
                        k--;
                        personRaised--;
                        if (p.size() == 1) {
                            k = p.size() + 1;
                        }
                    }
                    if (betType.toLowerCase().equals("call")) {
                        p.get(k).call(Player.prevBet - bets.get(k));
                        pot.add(Player.prevBet - bets.get(k));
                        prevAction.set(k, "call");
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (!betType.toLowerCase().equals("fold")) {
                        System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                + pot.getInPot() + " in the pot.");
                    }
                    System.out.println("Continue to next player? y/n");
                    ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("Continue to next player?");
                        ans = sc.next();
                    }
                    //Clear.clearScreen();
                }
                int peopleCalled = 0;
                for (int i = 0; i < prevAction.size(); i++) {
                    if (prevAction.get(i).equals("call")) {
                        peopleCalled++;
                    }
                }
                if (peopleCalled >= p.size() - 1) {
                    raised = false;
                }
            }
            System.out.println("The flop has now been dealt.");
            center = deck.dealCenter(0);
            Player.prevBet = 0;
            if (!(p.size() == 1)) {
                for (int k = tempDealer + 1; k < p.size(); k++) {
                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        ans = sc.next();
                    }
                    System.out.println(p.get(k));
                    System.out.print("The center contains: ");
                    for (int i = 0; i < center.size(); i++) {
                        System.out.print("     " + center.get(i).getCard());
                    }
                    System.out.println();
                    System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot.");
                    betType = sc.next();
                    while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                            betType.toLowerCase().equals("fold"))) {
                        System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                        betType = sc.next();
                    }
                    if (betType.toLowerCase().equals("raise")) {
                        System.out.println("How much?");
                        bet = sc.nextInt();
                        p.get(k).raise(bet);
                        pot.add(bet);
                        prevAction.set(k, "raise");
                        bets.set(k, bet);
                        raised = true;
                        personRaised = k;
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (betType.toLowerCase().equals("fold")) {
                        p.get(k).fold();
                        p.remove(k);
                        k--;
                        if (p.size() == 1) {
                            k = p.size() + 1;
                        }
                    }
                    if (betType.toLowerCase().equals("check")) {
                        p.get(k).check();
                    }
                    if (!betType.toLowerCase().equals("fold")) {
                        System.out.println("You now have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                    }
                    System.out.println("Continue to next player? y/n");
                    ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("Continue to next player?");
                        ans = sc.next();
                    }
                    if (raised) {
                        k = p.size();
                    }
                    //Clear.clearScreen();
                }
                if (!raised && !(p.size() == 1)) {
                    for (int k = 0; k <= tempDealer; k++) {

                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot.");
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much?");
                            bet = sc.nextInt();
                            p.get(k).raise(bet);
                            pot.add(bet);
                            prevAction.set(k, "raise");
                            bets.set(k, bet);
                            raised = true;
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            k--;
                            tempDealer--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("check")) {
                            p.get(k).check();
                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                    + pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        if (raised) {
                            k = p.size();
                        }
                        //Clear.clearScreen();
                    }
                }
                while (raised && !(p.size() == 1)) {
                    for (int k = personRaised + 1; k < p.size(); k++) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot. " +
                                    "The previous bet was $" + Player.prevBet);
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much? You will pay the specified amount + $" + Player.prevBet);
                            bet = sc.nextInt();
                            pot.add((bet + Player.prevBet) - bets.get(k));
                            p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                            prevAction.set(k, "raise");
                            bets.set(k, (bet + Player.prevBet) - bets.get(k));
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            if (k <= tempDealer) {
                                tempDealer--;
                            }
                            k--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("call")) {
                            p.get(k).call(Player.prevBet - bets.get(k));
                            pot.add(Player.prevBet - bets.get(k));
                            prevAction.set(k, "call");
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $" +
                                    pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        //Clear.clearScreen();
                    }
                    for (int k = 0; k < personRaised; k++) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot. " +
                                    "The previous bet was $" + Player.prevBet);
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much? You will pay the specified amount + $" +
                                    (Player.prevBet - bets.get(k)));
                            bet = sc.nextInt();
                            pot.add((bet + Player.prevBet) - bets.get(k));
                            p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                            prevAction.set(k, "raise");
                            bets.set(k, (bet + Player.prevBet) - bets.get(k));
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            if (k <= tempDealer) {
                                tempDealer--;
                            }
                            k--;
                            personRaised--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("call")) {
                            p.get(k).call(Player.prevBet - bets.get(k));
                            pot.add(Player.prevBet - bets.get(k));
                            prevAction.set(k, "call");
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }

                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                    + pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        //Clear.clearScreen();
                    }
                    int peopleCalled = 0;
                    for (int i = 0; i < p.size(); i++) {
                        if (prevAction.get(i).equals("call")) {
                            peopleCalled++;
                        }
                    }
                    if (peopleCalled >= p.size() - 1) {
                        raised = false;
                    }
                }
            }
            center.add(deck.dealCenter(1).get(0));
            Player.prevBet = 0;
            if (!(p.size() == 1)) {
                for (int k = tempDealer + 1; k < p.size(); k++) {
                    System.out.println("See " + p.get(k).getName() + " cards? y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        ans = sc.next();
                    }
                    System.out.println(p.get(k));
                    System.out.print("The center contains: ");
                    for (int i = 0; i < center.size(); i++) {
                        System.out.print("     " + center.get(i).getCard());
                    }
                    System.out.println();
                    System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                            ". There is $" + pot.getInPot() + " in the pot.");
                    betType = sc.next();
                    while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                            betType.toLowerCase().equals("fold"))) {
                        System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                        betType = sc.next();
                    }
                    if (betType.toLowerCase().equals("raise")) {
                        System.out.println("How much?");
                        bet = sc.nextInt();
                        p.get(k).raise(bet);
                        pot.add(bet);
                        prevAction.set(k, "raise");
                        bets.set(k, bet);
                        raised = true;
                        personRaised = k;
                        if (p.get(k).getMoney() < 0) {
                            pot.takeOut(Math.abs((p.get(k).getMoney())));
                            p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                        }
                    }
                    if (betType.toLowerCase().equals("fold")) {
                        p.get(k).fold();
                        p.remove(k);
                        k--;
                        if (p.size() == 1) {
                            k = p.size() + 1;
                        }
                    }
                    if (betType.toLowerCase().equals("check")) {
                        p.get(k).check();
                    }
                    if (!betType.toLowerCase().equals("fold")) {
                        System.out.println("You now have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                    }
                    System.out.println("Continue to next player? y/n");
                    ans = sc.next();
                    while (!ans.toLowerCase().equals("y")) {
                        System.out.println("Continue to next player?");
                        ans = sc.next();
                    }
                    if (raised) {
                        k = p.size();
                    }
                    //Clear.clearScreen();
                }
                if (!raised && !(p.size() == 1)) {
                    for (int k = 0; k <= tempDealer; k++) {

                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot.");
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("check") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Check, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot.");
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much?");
                            bet = sc.nextInt();
                            p.get(k).raise(bet);
                            pot.add(bet);
                            prevAction.set(k, "raise");
                            bets.set(k, bet);
                            raised = true;
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            k--;
                            tempDealer--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("check")) {
                            p.get(k).check();
                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                    + pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        if (raised) {
                            k = p.size();
                        }
                        //Clear.clearScreen();
                    }
                }
                while (raised && !(p.size() == 1)) {
                    for (int k = personRaised + 1; k < p.size(); k++) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot. " +
                                    "The previous bet was $" + Player.prevBet);
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much? You will pay the specified amount + $" + Player.prevBet);
                            bet = sc.nextInt();
                            pot.add((bet + Player.prevBet) - bets.get(k));
                            p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                            prevAction.set(k, "raise");
                            bets.set(k, (bet + Player.prevBet) - bets.get(k));
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            if (k <= tempDealer) {
                                tempDealer--;
                            }
                            k--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("call")) {
                            p.get(k).call(Player.prevBet - bets.get(k));
                            pot.add(Player.prevBet - bets.get(k));
                            prevAction.set(k, "call");
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $" +
                                    pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        //Clear.clearScreen();
                    }
                    for (int k = 0; k < personRaised; k++) {
                        System.out.println("See " + p.get(k).getName() + " cards? y/n");
                        String ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("See " + p.get(k).getName() + " cards? y/n");
                            ans = sc.next();
                        }
                        System.out.println(p.get(k));
                        System.out.print("The center contains: ");
                        for (int i = 0; i < center.size(); i++) {
                            System.out.print("     " + center.get(i).getCard());
                        }
                        System.out.println();
                        System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                ". There is $" + pot.getInPot() + " in the pot. " +
                                "The previous bet was $" + Player.prevBet);
                        betType = sc.next();
                        while (!(betType.toLowerCase().equals("call") || betType.toLowerCase().equals("raise") ||
                                betType.toLowerCase().equals("fold"))) {
                            System.out.println("Call, raise, or fold? You have $" + p.get(k).getMoney() +
                                    ". There is $" + pot.getInPot() + " in the pot. " +
                                    "The previous bet was $" + Player.prevBet);
                            betType = sc.next();
                        }
                        if (betType.toLowerCase().equals("raise")) {
                            System.out.println("How much? You will pay the specified amount + $" +
                                    (Player.prevBet - bets.get(k)));
                            bet = sc.nextInt();
                            pot.add((bet + Player.prevBet) - bets.get(k));
                            p.get(k).raise((bet + Player.prevBet) - bets.get(k));
                            prevAction.set(k, "raise");
                            bets.set(k, (bet + Player.prevBet) - bets.get(k));
                            personRaised = k;
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }

                        }
                        if (betType.toLowerCase().equals("fold")) {
                            p.get(k).fold();
                            p.remove(k);
                            if (k <= tempDealer) {
                                tempDealer--;
                            }
                            k--;
                            personRaised--;
                            if (p.size() == 1) {
                                k = p.size() + 1;
                            }
                        }
                        if (betType.toLowerCase().equals("call")) {
                            p.get(k).call(Player.prevBet - bets.get(k));
                            pot.add(Player.prevBet - bets.get(k));
                            prevAction.set(k, "call");
                            if (p.get(k).getMoney() < 0) {
                                pot.takeOut(Math.abs((p.get(k).getMoney())));
                                p.get(k).winRound(Math.abs(p.get(k).getMoney()));
                            }
                        }
                        if (!betType.toLowerCase().equals("fold")) {
                            System.out.println("You now have $" + p.get(k).getMoney() + ". There is $"
                                    + pot.getInPot() + " in the pot.");
                        }
                        System.out.println("Continue to next player? y/n");
                        ans = sc.next();
                        while (!ans.toLowerCase().equals("y")) {
                            System.out.println("Continue to next player?");
                            ans = sc.next();
                        }
                        //Clear.clearScreen();
                    }
                    int peopleCalled = 0;
                    for (int i = 0; i < p.size(); i++) {
                        if (prevAction.get(i).equals("call")) {
                            peopleCalled++;
                        }
                    }
                    if (peopleCalled >= p.size() - 1) {
                        raised = false;
                    }
                }
            }

            center.add(deck.dealCenter(2).get(0));

            if (p.size() > 1) {
                for (int i = 0; i < p.size(); i++) {
                    handCalc = new HandCalculator(center, p.get(i).getHand());
                    handCalc.handValue(p.get(i));
                }
                for (int i = 0; i < p.size(); i++) {
                    if (p.get(i).getHandValue()[0] > maxHandValue) {
                        maxHandValue = p.get(i).getHandValue()[0];
                    }
                }
                for (int i = 0; i < p.size(); i++) {
                    if (p.get(i).getHandValue()[0] < maxHandValue) {
                        p.remove(i);
                        i--;
                    }
                }
                if (p.size() > 1) {
                    maxHandValue = 0;
                    for (int i = 0; i < p.size(); i++) {
                        if (p.get(i).getHandValue()[1] > maxHandValue) {
                            maxHandValue = p.get(i).getHandValue()[1];
                        }
                    }
                    for (int i = 0; i < p.size(); i++) {
                        if (p.get(i).getHandValue()[1] < maxHandValue) {
                            p.remove(i);
                            i--;
                        }
                    }
                    if (p.size() > 1) {
                        maxHandValue = 0;
                        for (int i = 0; i < p.size(); i++) {
                            if (p.get(i).getHandValue()[2] > maxHandValue) {
                                maxHandValue = p.get(i).getHandValue()[2];
                            }
                        }
                        for (int i = 0; i < p.size(); i++) {
                            if (p.get(i).getHandValue()[2] < maxHandValue) {
                                p.remove(i);
                                i--;
                            }
                        }
                    }
                }
            }
            System.out.print("The center contains: ");
            for (int i = 0; i < center.size(); i++) {
                System.out.print("     " + center.get(i).getCard());
            }
            System.out.println();
            for (int i = 0; i < p.size(); i++) {
                System.out.println(p.get(i).getName() + " has: " + p.get(i));
            }
            if (p.size() > 1) {
                if (pot.getInPot() % p.size() == 0) {
                    for (int i = 0; i < p.size(); i++) {
                        System.out.println(p.get(i).getName() + " has won the round and gotten $" + pot.getInPot() / p.size());
                        p.get(i).winRound(pot.getInPot() / p.size());
                    }
                } else {
                    for (int i = 0; i < p.size(); i++) {
                        p.get(i).winRound(pot.getInPot() / p.size());
                    }
                    int rand = ran.nextInt(p.size());
                    p.get(rand).winRound(pot.getInPot() % p.size());
                    for (int i = 0; i < p.size(); i++) {
                        if (!(i == rand)) {
                            System.out.println(p.get(i).getName() + " has won the round and gotten $" + pot.getInPot() / p.size());
                        }
                    }
                    System.out.println(p.get(rand).getName() + " has won the round and gotten $" +
                            (pot.getInPot() / p.size() + pot.getInPot() % p.size()));
                }
            } else {
                p.get(0).winRound(pot.getInPot());
                System.out.println(p.get(0).getName() + " has won the round and gotten $" + pot.getInPot());
            }
            for (int i = 0; i < p.size(); i++) {
                p.remove(i);
                i--;
            }
            for (int i = 0; i < originalP.size(); i++) {
                p.add(originalP.get(i));
            }
            int noMoney = 0;
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).getMoney() <= 0) {
                    noMoney++;
                }
                p.get(i).fold();
            }
            if (noMoney >= p.size() - 1) {
                endGame = true;
            } else {
                int wantToEndGame = 0;
                for (int i = 0; i < p.size(); i++) {
                    System.out.println("Would you like to end the game? If half of the players want to end, the game will end. y/n");
                    String ans = sc.next();
                    while (!ans.toLowerCase().equals("y") && !ans.toLowerCase().equals("n")) {
                        System.out.println("Would you like to end the game? If 50% or more players want to end, the game will end. y/n");
                        ans = sc.next();
                    }
                    if (ans.toLowerCase().equals("y")) {
                        wantToEndGame++;
                    }
                }
                if (wantToEndGame >= p.size() / 2) {
                    endGame = true;
                }
            }
            if (endGame) {
                int maxMoney = 0;
                int playerMax = 0;
                for (int i = 0; i < p.size(); i++) {
                    if (p.get(i).getMoney() > maxMoney) {
                        playerMax = i;
                        maxMoney = p.get(i).getMoney();
                    }
                }
                System.out.println("The game will now end.");
                System.out.println("The winner is " + p.get(playerMax).getName() + " with $" + p.get(playerMax).getMoney());
            }
        }
    }
}
