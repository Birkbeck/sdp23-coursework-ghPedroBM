package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class OutInstructionTest {
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
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
    }

    @Test
    void testToString() {
        Instruction instruction = new OutInstruction(null, EAX);
        String expectedString = "out EAX";
        String actualString = instruction.toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testEqualsTrue() {
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EAX);
        Assertions.assertTrue(instruction1.equals(instruction2));
        Assertions.assertTrue(instruction2.equals(instruction1));
    }

    @Test
    void testEqualsFalse() {
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
        Assertions.assertFalse(instruction2.equals(instruction1));
    }

    @Test
    void testHashCodeTrue() {
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EAX);
        Assertions.assertTrue(instruction1.hashCode()==instruction2.hashCode());
    }

    void testHashCodeFalse() {
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EBX);
        Assertions.assertFalse(instruction1.hashCode()==instruction2.hashCode());
    }

}