package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * @author Pedro Bustamante Munguira
 */

public class MovInstruction extends Instruction {

    private final RegisterName result;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(result);
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }
}
