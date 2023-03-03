package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class JnzInstructionTest {
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
        //registers.set(EAX, 5);
        //Instruction instruction = new JnzInstruction(null, EAX, "f3");
        //instruction.execute(machine);
        //Assertions.assertEquals(4, instruction.execute(machine));
    }

    @Test
    void executeValidTwo() {
        //registers.set(EAX, 5);
        //Instruction instruction = new JnzInstruction(null, EAX, "f3");
        //instruction.execute(machine);
        //Assertions.assertEquals(4, machine.getLabels().getAddress("f3"));
    }

    // Test toString
    @Test
    void testToStringNullLabel() {
        Instruction instruction = new JnzInstruction(null, EAX, "labelNextStatementTest");
        String expectedString = "jnz EAX labelNextStatementTest";
        String actualString = instruction.toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringNonNullLabel() {
        Instruction instruction = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        String expectedString = "l 1: jnz EAX labelNextStatementTest";
        String actualString = instruction.toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    // Test equals
    @Test
    void testEqualsTrueNullLabel() {
        Instruction instruction1 = new JnzInstruction(null, EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction(null, EAX, "labelNextStatementTest");
        Assertions.assertTrue(instruction1.equals(instruction2));
        Assertions.assertTrue(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsTrueNonNullLabel() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Assertions.assertTrue(instruction1.equals(instruction2));
        Assertions.assertTrue(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalseNullLabelDifferentNextStatementLabel() {
        Instruction instruction1 = new JnzInstruction(null, EAX, "labelNextStatementTest1");
        Instruction instruction2 = new JnzInstruction(null, EBX, "labelNextStatementTest2");
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalseNonNullLabelDifferentNextStatementLabels() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest1");
        Instruction instruction2 = new JnzInstruction("l 1", EBX, "labelNextStatementTest2");
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalseNonNullLabelDifferentLabels() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction("l1", EAX, "labelNextStatementTest");
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    // Test hashCode
    @Test
    void testHashCodeTrueNullLabels() {
        Instruction instruction1 = new JnzInstruction(null, EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction(null, EAX, "labelNextStatementTest");
        Assertions.assertTrue(instruction1.hashCode()==instruction2.hashCode());
    }

    @Test
    void testHashCodeTrueNonNullLabels() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Assertions.assertTrue(instruction1.hashCode()==instruction2.hashCode());
    }

    @Test
    void testHashCodeFalseNonNullLabelsDifferentNextStatementLabels() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest1");
        Instruction instruction2 = new JnzInstruction("l 1", EBX, "labelNextStatementTest2");
        Assertions.assertFalse(instruction1.hashCode()==instruction2.hashCode());
    }

    void testHashCodeFalseNonNullLabelsDifferentLabels() {
        Instruction instruction1 = new JnzInstruction("l 1", EAX, "labelNextStatementTest");
        Instruction instruction2 = new JnzInstruction("l1", EBX, "labelNextStatementTest");
        Assertions.assertFalse(instruction1.hashCode()==instruction2.hashCode());
    }

}