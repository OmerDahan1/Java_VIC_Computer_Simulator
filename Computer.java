
/**
 * Represents a Vic computer.
 * It is assumed that users of this class are familiar with the Vic computer,
 * and the Vic machine language, described in www1.idc.ac.il/vic.
 * <br/> The Computer's hardware consists of the following components:
 * <UL>
 * <LI> Data register: a register.
 * <LI> Program counter: a register.
 * <LI> Input unit: a stream of numbers. In this implementation, the input unit is simulated
 * by a text file. When the computer is instructed to execute a READ instruction, it reads
 * the next number from this file and puts it in the data register.
 * <LI> Output unit: a stream of numbers. In this implementation, the output unit is simulated by
 * standard output (by default, the console).
 * When the computer is instructed to execute a WRITE instruction, it writes the current
 * value of the data register to the standard output.
 * <LI> Processor: In this implementation, the processor is emulated by the run method of this class.
 * </UL>
 * The Computer executes programs written in the numeric Vic machine language.
 * The program is stored in a text file that can be loaded into the computer's memory.
 * This is done by the loadProgram method of this class.
 */

public class Computer {

    /**
     * This constant represents the size of the memory unit of this Computer
     * (number of memory registers).
     */
    public final static int MEM_SIZE = 100;

    /**
     * This constant represents the memory address at which the constant 0 is stored.
     */
    public final static int LOCATION_OF_ZERO = MEM_SIZE - 2;

    /**
     * This constant represents the memory address at which the number 1 is stored.
     */
    public final static int LOCATION_OF_ONE = MEM_SIZE - 1;

    // Op-code definitions:
    private final static int ADD = 1;
    private final static int SUB = 2;
    private final static int LOAD = 3;
    private final static int STORE = 4;
    private final static int GOTO = 5;
    private final static int GOTOZ = 6;
    private final static int GOTOP = 7;
    private final static int READ = 8;
    private final static int WRITE = 9;
    private final static int STOP = 0;


    // Put the rest of the op-code definitions here.

    /**
     * The Computer consists of a Memory unit, and two registers, as follows:
     */
    private Memory m;
    private Register dReg;
    private Register pc;

    /**
     * Constructs a Vic computer. Specifically:
     * Constructs a memory that has MEM_SIZE registers, a data register,
     * and a program counter. Next, resets the computer (see the reset method API).
     * <p>
     * Note: the initialization of the input unit and the loading of a program into
     * memory are not done by the constructor. This is done by the public methods
     * loadInput and loadProgram, respectively.
     */
    public Computer() {
        this.m = new Memory(MEM_SIZE);
        this.pc = new Register();
        this.dReg = new Register();
        this.reset();
    }

    /**
     * Resets the computer. Specifically:
     * Resets the memory, sets the memory registers at addresses LOCATION_OF_ZERO
     * and LOCATION_OF_ONE to 0 and to 1, respectively, sets the data register
     * and the program counter to 0.
     */
    public void reset() {
        m.reset();
        m.setValue(LOCATION_OF_ZERO, 0);
        m.setValue(LOCATION_OF_ONE, 1);
        dReg.setValue(0);
        pc.setValue(0);

    }

    /**
     * Executes the program currently stored in memory.
     * This is done by affecting the following fetch-execute cycle:
     * Fetches from memory the next instruction (3-digit number), i.e. the contents of the
     * memory register whose address is the current value of the program counter.
     * Extracts from this word the op-code (left-most digit) and the address (next 2 digits).
     * Next, executes the command specified by the op-code, using the address if necessary.
     * As a side-effect of executing the instruction, modifies the program counter.
     * Next, loops to fetch the next instruction, and so on.
     */
    public void run() {
        int counter = 0;
        while (m.getValue(counter) != 0) {
            int operation = m.getValue(counter) / 100;
            int address = m.getValue(counter) % 100;
            counter++;
            if (operation == ADD) {
                execAdd(address);
            } else if (operation == SUB) {
                execSub(address);
            } else if (operation == LOAD) {
                execLoad(address);
            } else if (operation == STORE) {
                execStore(address);
            }else if (operation == GOTO){
                execGoto(address);
            }else if(operation == GOTOZ){
                execGotoz(address);
            }else if(operation == GOTOP){
                execGotop(address);
            }else if (operation == READ) {
                execRead();
            }else if (operation == WRITE) {
                execWrite();
            }

        }
        execStop();
    }

    // Private execution routines, one for each Vic command
    private void execAdd(int addr) {
        dReg.setValue(dReg.getValue() + m.getValue(addr));
        pc.addOne();
    }

    private void execSub(int addr) {
        dReg.setValue(dReg.getValue() - m.getValue(addr));
        pc.addOne();
    }

    private void execLoad(int addr) {
        dReg.setValue(m.getValue(addr));
        pc.addOne();
    }

    private void execStore(int addr) {
        m.setValue(addr, dReg.getValue());
        pc.addOne();
    }

    private void execGoto(int value) {
        pc.setValue(value);
    }

    private void execGotoz(int value) {
        if (dReg.getValue() == 0) {
            pc.setValue(value);
        }else {
            pc.addOne();
        }
    }

    private void execGotop(int value) {
        if (dReg.getValue() > 0) {
            pc.setValue(value);
        }else {
            pc.addOne();
        }
    }

    private void execRead() {
        int register = StdIn.readInt();
        dReg.setValue(register);
        pc.addOne();
    }

    private void execWrite() {
        System.out.println(dReg.getValue());
        pc.addOne();
    }

    private void execStop() {
        System.out.println("Program terminated normally");
        pc.addOne();
    }


    // Implement the other private methods here (execRead, execWrite, execAdd, etc.).
    // For each mehod, you have to write its siganture, and implement it.

    /**
     * Loads a program into memory, starting at address 0, using the standard input.
     * The program is stored in a text file whose name is the given fileName.
     * It is assumed that the file contains a stream of valid commands written
     * in the numeric Vic machine language (described in www1.idc.ac.il/vic).
     * The program is stored in the memory, starting at address 0.
     */
    public void loadProgram(String fileName) {
        StdIn.setInput(fileName);
        int i = 0;
        while (StdIn.hasNextLine()) {
            m.setValue(i, StdIn.readInt());
            i++;
        }
        // Write below the code that reads the Vic nstructions
        // from StdIn and stores them in the computer memory.
    }

    /**
     * Initializes the input unit from a given text file using the standard input.
     * It is assumed that the file contains a stream of valid data values,
     * each being an integer in the range -999 to 999.
     * Each time the computer is instructed to execute a READ instruction,
     * the next line from this file is read and placed in the data register
     * (this READ logic is part of the run method implementation).
     * The role of this method is to initialize the file in order to
     * enable the execution of subsequent READ commands.
     */
    public void loadInput(String fileName) {
        StdIn.setInput(fileName);

    }

    /**
     * This method is used for debugging purposes.
     * It displays the current contents of the data register,
     * the program counter, and the first and last 10 memory cells.
     */
    public String toString() {
        String s = "D Register: = " + dReg.getValue() + "\n";
        s += "PC Register: = " + pc.getValue() + "\n";
        s += "Memory state: " + "\n";
        s += m.toString();
        return s;
    }
}