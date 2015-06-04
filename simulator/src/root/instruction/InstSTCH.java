package root.instruction;

import root.Constants;
import root.VirtualMachine;

/**
 * Created by loki on 15. 6. 4..
 */
public class InstSTCH extends SICXEInstruction {
    public InstSTCH(byte[] bytes, boolean e) {
        super(bytes, e);
    }

    @Override
    public void Execute(VirtualMachine virtualMachine) {
        int address = getDestAddress(virtualMachine);
        int regValue = virtualMachine.getRegister(Constants.REGISTER_A);
        virtualMachine.setMemory(address, getByteFromRegisterValue(regValue), 1);
    }
}
