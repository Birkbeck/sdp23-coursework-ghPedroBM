package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MulInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-30, machine.getRegisters().get(EAX));
    }

    // Test toString
    @Test
    void testToStringNullLabel() {
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        String expectedString = "mul EAX EBX";
        String actualString = instruction.toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringNonNullLabel() {
        Instruction instruction = new MulInstruction("l 1", EAX, EBX);
        String expectedString = "l 1: mul EAX EBX";
        String actualString = instruction.toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testEqualsTrueNullLabel() {
        Instruction instruction1 = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, EBX);
        Assertions.assertTrue(instruction1.equals(instruction2));
        Assertions.assertTrue(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsTrueNonNullLabel() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EBX);
        Instruction instruction2 = new MulInstruction("l 1", EAX, EBX);
        Assertions.assertTrue(instruction1.equals(instruction2));
        Assertions.assertTrue(instruction2.equals(instruction1));
    }

    // Test equals
    @Test
    void testEqualsFalseNullLabelDifferentRegisters() {
        Instruction instruction1 = new MulInstruction(null, EAX, EAX);
        Instruction instruction2 = new MulInstruction(null, EBX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalseNonNullLabelDifferentRegisters() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EAX);
        Instruction instruction2 = new MulInstruction("l 1", EBX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalseNonNullLabelDifferentLabels() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EAX);
        Instruction instruction2 = new MulInstruction("l1", EAX, EAX);
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    // Test hashCode
    @Test
    void testHashCodeTrueNullLabels() {
        Instruction instruction1 = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, EBX);
        Assertions.assertTrue(instruction1.hashCode()==instruction2.hashCode());
    }

    @Test
    void testHashCodeTrueNonNullLabels() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EBX);
        Instruction instruction2 = new MulInstruction("l 1", EAX, EBX);
        Assertions.assertTrue(instruction1.hashCode()==instruction2.hashCode());
    }

    @Test
    void testHashCodeFalseNonNullLabelsDifferentRegisters() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EAX);
        Instruction instruction2 = new MulInstruction("l 1", EBX, EBX);
        Assertions.assertFalse(instruction1.hashCode()==instruction2.hashCode());
    }

    void testHashCodeFalseNonNullLabelsDifferentLabels() {
        Instruction instruction1 = new MulInstruction("l 1", EAX, EAX);
        Instruction instruction2 = new MulInstruction("l1", EBX, EBX);
        Assertions.assertFalse(instruction1.hashCode()==instruction2.hashCode());
    }

}