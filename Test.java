// A basic test class. Feel free to extend it, as needed.
public class Test {
	
	public static void main (String args[]) {		
		// registerTest();
        // memoryTest();
        computerTest();
    }	
	
    public static void registerTest () {	
		Register r1 = new Register();
        r1.setValue(10);
        System.out.println(r1);
        Register r2 = new Register(20);
        System.out.println(r2);
        r1.addOne();
        r2.setValue(r1.getValue());
        System.out.println(r2);
    }
    
    public static void memoryTest () {
    	Memory m = new Memory(100);
    	m.setValue(0, 100);
		m.setValue(1, 200);
		m.setValue(2, -17);
		System.out.println(m.getValue(0));
		m.setValue(1, 300);
		System.out.println(m);
    }

    public static void computerTest(){
        Computer vic = new Computer();
        vic.loadProgram("program5.vic"); // or some other program file
        vic.loadInput("input1.txt");
        vic.run();

        System.out.println(vic);
    }
}
