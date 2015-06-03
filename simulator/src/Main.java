import interfaces.SicSimulator;

import java.io.File;

/**
 * Created by loki on 15. 5. 28..
 */
public class Main {
    public static void main(String[] args) {
        VirtualMachine virtualMachine = new VirtualMachine();
        GUISimulator guiSimulator = new GUISimulator();
        CodeSimulator codeSimulator = new CodeSimulator();
        ObjectCodeLoader objectCodeLoader = new ObjectCodeLoader();

        virtualMachine.setGuiSimulator(guiSimulator);
        virtualMachine.setCodeSimulator(codeSimulator);
        virtualMachine.setObjectCodeLoader(objectCodeLoader);

        guiSimulator.setVirtualMachine(virtualMachine);
        guiSimulator.setCodeSimulator(codeSimulator);

        codeSimulator.setVirtualMachine(virtualMachine);
        codeSimulator.setGuiSimulator(guiSimulator);

        objectCodeLoader.setVirtualMachine(virtualMachine);
        objectCodeLoader.setCodeSimulator(codeSimulator);


        objectCodeLoader.load(new File("program_copy"));
    }
}
