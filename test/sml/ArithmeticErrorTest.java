package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;
import sml.instruction.AddInstruction;

import java.io.IOException;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

public class ArithmeticErrorTest {

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
    void divisionByZeroTest() {
        try {
            String inputFileName = "division0.txt";
            Translator t = new Translator(inputFileName);
            Machine m = new Machine(new Registers());
            t.readAndTranslate(m.getLabels(), m.getProgram());
            m.execute();
        } catch (IOException e) {
            Assertions.assertTrue(e.getMessage() == "/ by 0");
        }
    }

}
