import java.util.ArrayList;

/**
 * Write a description of class Typist here.
 *
 * Starter code generously abandoned by Ty Posaurus, your predecessor,
 * who typed with two fingers and considered that "good enough".
 * He left a sticky note: "the slide-back thing is optional probably".
 * It is not optional. Good luck.
 *
 * @author (Chennuo Lin)
 * @version (0.0.1)
 */
public class Typist
{
    // Fields of class Typist
    // Hint: you will need six fields. Think carefully about their types.
    // One of them tracks how far along the passage the typist has reached.
    // Another tracks whether the typist is currently burnt out.
    // A third tracks HOW MANY turns of burnout remain (not just whether they are burnt out).
    // The remaining three should be fairly obvious.

    //*@*@
    private String name; // Type Name
    private char symbol; // Unicode 1,2,3
    private int progress; // Progress of document execution
    private boolean burntOut; // Die
    private int burnoutTurnsRemaining; //The remaining progress of the document
    private double accuracy; //0.0-1.0
    private ArrayList<Integer> recordRanking;
    private ArrayList<Double> WPMlist;

    //part2
    private String[] equipmentArray;
    //*@*@


    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy, String[] equipmentArray)
    {

        this.symbol = typistSymbol;

        this.name = typistName;

        this.progress = 0;

        this.burntOut = false;

        this.burnoutTurnsRemaining = 0;

        this.equipmentArray = equipmentArray;

        recordRanking = new ArrayList<>();
        WPMlist = new ArrayList<>();
        WPMlist.add(0.0);

        setAccuracy(typistAccuracy);

    }


    // Methods of class Typist

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {

        //turn ture it be stop
        this.burntOut = true;

        //set last round
        this.burnoutTurnsRemaining = turns;


    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {

        if(burntOut){//It will only start when "burntOut" is set to "true".

            burnoutTurnsRemaining--;//Reduce the remaining rounds by one

            if (burnoutTurnsRemaining <= 0){//hen the counter reaches zero, the typist recovers automatically.
                burntOut = false;
                burnoutTurnsRemaining = 0;
            }
        }

    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return this.accuracy; // placeholder - replace with correct implementation
    }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return this.progress; // placeholder - replace with correct implementation
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return this.name; // placeholder - replace with correct implementation
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return this.symbol; // placeholder - replace with correct implementation
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        return this.burnoutTurnsRemaining; // placeholder - replace with correct implementation
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {

        this.progress = 0;
        this.burntOut = false;
        this.burnoutTurnsRemaining = 0;

    }

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return this.burntOut; // placeholder - replace with correct implementation
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        if (!isBurntOut()){
            this.progress++;
        }
    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        if(!isBurntOut()){
            //only die can go back
            this.progress = this.progress - amount;

            //if progress is 0 it means can't go
            if (this.progress < 0){
                this.progress = 0;
            }
        }
    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        //if smaller than 0.0 set it 0.0
        if (newAccuracy < 0.0){

            this.accuracy = 0.0;
        //if larger than 1.0 set 1.0
        }else if (newAccuracy > 1.0){

            this.accuracy = 1.0;

        }else{
            this.accuracy = newAccuracy;
        }

    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

    //part2
    public String[] getEqu(){
        return equipmentArray;
    }


    public ArrayList<Integer> getDataR(){
        return recordRanking;
    }


    public void addDataRanking(int num){
        recordRanking.add(num);
    }

    public String[] getDataRankingNumber(){

        String[] renterArrays = new String[recordRanking.size()];
        for (int i = 0; i < recordRanking.size(); i++) {
            int item = recordRanking.get(i);
            renterArrays[i] = String.valueOf(item);
        }

        return renterArrays;

    }

    public int[] CountScore(){

        int one = 0;
        int two = 0;
        int three = 0;

        for(int n:recordRanking){
            if (n == 1) one++;
            if (n == 2) two++;
            if (n == 3) three++;
        }

        return new int[]{one, two, three};
    }

    public int sumScoreR(){
        int sum = 0;

        for (int i = 0; i < recordRanking.size(); i++) {
            int item = recordRanking.get(i);
            System.out.println(item);
            if (item == 1)sum+=3;
            if (item == 2)sum+=2;
            if (item == 3)sum+=1;
        }
        return sum;

    }

    public void addWPM(double n){
        WPMlist.add(n);
    }

    public double getWPM(){

        double sum = 0.0;

        for(double n : WPMlist){
            sum+=n;
        }

        return sum;

    }

}
