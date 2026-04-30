public class Typist_Test {
    public static void main(String[] args) {
        
	//Test1 go forward
        Typist t1 = new Typist('①', "Test1", 0.8);
        t1.typeCharacter();
        t1.typeCharacter();
        System.out.println("Test1 Progress: " + t1.getProgress());

        // Test2 backward, beyond the limit
        Typist t2 = new Typist('②', "Test2", 0.5);
        t2.slideBack(10);
        System.out.println("Test2 Progress: " + t2.getProgress()); 

        // Test3 range 0.0-1.0
        Typist t3 = new Typist('③', "Test3", 1.5);
        System.out.println("Test3 Accuracy: " + t3.getAccuracy());

        // Test4 remaining distance
        Typist t4 = new Typist('④', "Test4", 0.7);
        t4.burnOut(2);
        t4.recoverFromBurnout();
        System.out.println("Test4 BTR: " + t4.getBurnoutTurnsRemaining()); 

        // Test5 reset
        Typist t5 = new Typist('⑤', "Test5", 0.6);
        t5.typeCharacter();
        t5.resetToStart();
        System.out.println("Test5 Progress: " + t5.getProgress());
    }
}