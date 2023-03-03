package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents a mov (store integer in a register) instruction
 *
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
        return getLabelString() + getOpcode() + " " + result + " " + x.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MovInstruction that = (MovInstruction) o;
        return Objects.equals(result, that.result) && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, x);
    }

}
