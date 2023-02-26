package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents a <b>J</b>ump if <b>N</b>ot <b>Z</b>ero instruction
 *
 * @author Pedro Bustamante Munguira
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String labelNextStatement;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String labelNextStatement) {
        super(label, OP_CODE);
        this.source = source;
        this.labelNextStatement = labelNextStatement;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        if (value != 0) {
            return m.getLabels().getAddress(this.labelNextStatement);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + labelNextStatement;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JnzInstruction that = (JnzInstruction) o;
        return Objects.equals(source, that.source) && Objects.equals(labelNextStatement, that.labelNextStatement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), source, labelNextStatement);
    }
}
