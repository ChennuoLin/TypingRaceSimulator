public class TypingRace_Test {
    public static void main(String[] args) {
        TypingRace race = new TypingRace(30);
        
        race.addTypist(new Typist('①', "Fast", 0.9), 1);
        race.addTypist(new Typist('②', "Normal", 0.7), 2);
        race.addTypist(new Typist('③', "Slow", 0.5), 3);
        
        race.startRace();
    }
}