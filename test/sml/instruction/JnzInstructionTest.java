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
}