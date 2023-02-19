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
    private final Integer x;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, Integer x) {
        super(label, OP_CODE);
        this.result = result;
        this.x = x;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, x);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }
}
