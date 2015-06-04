package root;

import root.interfaces.ResourceManager;

/**
 * Created by loki on 15. 6. 3..
 */
public class VirtualMachine implements ResourceManager {

    private GUISimulator guiSimulator;
    private CodeSimulator codeSimulator;
    private ObjectCodeLoader objectCodeLoader;

    private int[] registers;
    private byte[] memory;
    private int lastMemoryAddress;
    private String programName;
    private int currInstructionSize;


    public VirtualMachine() {
        initializeMemory();
        initializeRegister();
    }

    @Override
    public void initializeMemory() {
        registers = new int[16];
        memory = new byte[2 << 15];
        lastMemoryAddress = 0;
    }

    @Override
    public void initializeRegister() {
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }
    }

    @Override
    public void initialDevice(String devName) {

    }

    @Override
    public void setMemory(int locate, byte[] data, int size) {
        for (int i = 0; i < size; i++) {
            memory[locate + i] = data[i];
        }
    }

    @Override
    public void setRegister(int regNum, int value) {
        registers[regNum] = value;
    }

    @Override
    public byte[] getMemory(int locate, int size) {
        byte[] ret = new byte[size];
        for (int i = 0; i < size; i++) {
            ret[i] = memory[locate + i];
        }

        return ret;
    }

    public byte[] getMemory() {
        return memory;
    }

    @Override
    public int getRegister(int regNum) {
        return registers[regNum];
    }

    @Override
    public void affectVisualSimulator() {
        guiSimulator.updateRegisters(this.registers);
        guiSimulator.updateProgramInformation(programName, lastMemoryAddress);
        guiSimulator.updateMemoryDump();
    }

    public int reserveMemory(int size) {
        int prev = lastMemoryAddress;
        lastMemoryAddress += size;

        return prev;
    }

    public void setGuiSimulator(GUISimulator guiSimulator) {
        this.guiSimulator = guiSimulator;
    }

    public void setCodeSimulator(CodeSimulator codeSimulator) {
        this.codeSimulator = codeSimulator;
    }

    public void setObjectCodeLoader(ObjectCodeLoader objectCodeLoader) {
        this.objectCodeLoader = objectCodeLoader;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getRegisterPC() {
        return registers[Constants.REGISTER_PC];
    }

    public int getCurrMemoryIndex() {
        return registers[Constants.REGISTER_PC] - currInstructionSize;
    }

//    public void setCurrMemoryIndex(int currMemoryIndex) {
//        registers[Constants.REGISTER_PC] = currMemoryIndex + currInstructionSize;
//    }

    public void moveToNextPC() {
        registers[Constants.REGISTER_PC] += currInstructionSize;
    }

    public int getCurrInstructionSize() {
        return currInstructionSize;
    }

    public void setCurrInstructionSize(int currInstructionSize) {
        this.currInstructionSize = currInstructionSize;
        registers[Constants.REGISTER_PC] = getCurrMemoryIndex() + currInstructionSize;
    }

    public void printMemoryDump() {
        for(int i = 0; i < memory.length; i ++) {
            if( i % 4 == 0 && i > 0 ) {
                System.out.print(" ");
            }
            if( i % 16 == 0 && i > 0 ) {
                System.out.println();
            }

            char c1 = (char) ((memory[i] & 0x000000F0) >> 4);
            char c2 = (char) (memory[i] & 0x0000000F);

            System.out.print(Util.digitToHex(c1));
            System.out.print(Util.digitToHex(c2));
        }

    }
}