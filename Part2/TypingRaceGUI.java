import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TypingRaceGUI {

    private int passageLength;   // Total characters in the passage to type
    private ArrayList<Typist> seatTypist;
    private double MISTYPE_BASE_CHANCE_moudl;
    private int BURNOUT_DURATION_moudl;
    private int SLIDE_BACK_AMOUNT_moudl;
    private String modul;


    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static final int SLIDE_BACK_AMOUNT   = 2;
    private static final int BURNOUT_DURATION     = 3;

    private static MainStartDisplay GameDisplay;

    public TypingRaceGUI(int passageLength, String modul , ArrayList<Typist> seatTypist ,MainStartDisplay GameDisplay){

        this.passageLength = passageLength;
        if(modul.equals("Coffee mode")){
            this.BURNOUT_DURATION_moudl = -2;
            this.SLIDE_BACK_AMOUNT_moudl = -2;
        }

        if(modul.equals("Night shift mode")){
            this.MISTYPE_BASE_CHANCE_moudl = 0.2;
        }
        this.modul = modul;
        this.seatTypist = seatTypist;

        this.GameDisplay = GameDisplay;

    }

    public void startRace()
    {
        boolean finished = false;

        int rounds = 0;

        for(Typist n : seatTypist){
            n.resetToStart();
        }

        while(!finished) {

            rounds++;

            for (Typist n : seatTypist) {
                advanceTypist(n);
            }

            printRace();

            for (Typist n : seatTypist) {
                if (raceFinishedBy(n)) {
                    finished = true;
                }
            }


            GameDisplay .getDisplayArea().paintImmediately(0, 0, 1000, 1000);
            try { Thread.sleep(200); } catch (Exception e) {}
        }

        for(Typist n : seatTypist) {
            if (raceFinishedBy(n)) {
                JOptionPane.showMessageDialog(null, "Finish! "+n.getName()+" is Winner!( Symbol "+n.getSymbol()+")");

            }
        }

        seatTypist.sort(Comparator.comparing(Typist::getProgress).reversed());
        int numranking = 1;
        GameDisplay.clearDisplay();
        GameDisplay.showText(GameDisplay.multiplePrint('=',25));
        for (Typist n : seatTypist){

            double WPM = n.getTotleword()/((rounds*200)/1000.0);

            n.addWPM(WPM);

            n.addDataRanking(numranking);

            n.addburnoutTimeList(n.getBurnoutTime());

            GameDisplay.ShowWPM(numranking,n);

            numranking++;
        }
        GameDisplay.showText(GameDisplay.multiplePrint('=',25));


    }

    private void advanceTypist(Typist theTypist)
    {
        if (theTypist.isBurntOut()) {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }

        // Attempt to type a character
        if (Math.random() < theTypist.getAccuracy())
        {
            theTypist.typeCharacter();
            theTypist.addTotleword();
        }

        // Mistype check — the probability should reflect the typist's accuracy
        if (Math.random() < theTypist.getAccuracy() * MISTYPE_BASE_CHANCE+MISTYPE_BASE_CHANCE_moudl)
        {
            theTypist.slideBack(SLIDE_BACK_AMOUNT+SLIDE_BACK_AMOUNT_moudl);
        }


        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy squared, capped at ~0.05)
        if (Math.random() < 0.05 * theTypist.getAccuracy() * theTypist.getAccuracy())
        {
            theTypist.burnOut(BURNOUT_DURATION+BURNOUT_DURATION_moudl);
            theTypist.addburnoutTime();
        }

    }

    private void printRace()
    {
        GameDisplay.clearDisplay();

        GameDisplay.showText("  TYPING RACE: "+modul+" — passage length: " + passageLength + " chars");

        GameDisplay.showText(GameDisplay.multiplePrint('=', passageLength + 3));

        for(Typist n : seatTypist) {
            printSeat(n);
        }

        GameDisplay.showText(GameDisplay.multiplePrint('=', passageLength + 3));
        GameDisplay.showText("  [zz] = burnt out    [<] = just mistyped");
    }

    private void printSeat(Typist theTypist)
    {
        int spacesBefore = theTypist.getProgress();
        int spacesAfter  = passageLength - theTypist.getProgress();

        String OutPutString = "|"+GameDisplay.multiplePrint(' ', spacesBefore)+theTypist.getSymbol();

        // Always show the typist's symbol so they can be identified on screen.
        // Append ~ when burnt out so the state is visible without hiding identity.
        if (theTypist.isBurntOut())
        {
            OutPutString+="~";
            spacesAfter--; // symbol + ~ together take two characters
        }

        OutPutString=OutPutString+GameDisplay.multiplePrint(' ', spacesAfter)+"|";

        // Print name and accuracy
        if (theTypist.isBurntOut())
        {
            OutPutString+=(theTypist.getName()
                    + " (Accuracy: " + theTypist.getAccuracy() + ")"
                    + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
        }
        else
        {
            OutPutString+=(theTypist.getName()
                    + " (Accuracy: " + theTypist.getAccuracy() + ")");
        }

        GameDisplay.showText(OutPutString);
    }
    private boolean raceFinishedBy(Typist theTypist)
    {
        // Ty was confident this condition was correct
        //(==) If it is greater than passagelength, it will trigger an infinite loop.
        //so change (>=)
        if (theTypist.getProgress() >= passageLength)//@@@@
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
