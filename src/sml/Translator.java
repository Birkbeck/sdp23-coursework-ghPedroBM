package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (line.isEmpty())
            return null;

        String opcode = scan();

        // TODO: Then, replace the switch by using the Reflection API


        // Class
        String opcodeClassName = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
        Class<?> opcodeClass = Class.forName(opcodeClassName);


        // Constructors
        Constructor<?>[] opcodeClassConstructors = opcodeClass.getConstructors();
        Constructor ctor0 = opcodeClassConstructors[0];
        

        // Parameters
        int ctr0ParamCount = ctor0.getParameterCount();
        Class<?>[] ctr0ParamTypes  = ctor0.getParameterTypes();

        int smlParamCount = ctr0ParamCount- 1;
        String[] smlScans = new String[smlParamCount];
        for (int i = 0; i < smlParamCount; i++) {
            smlScans[i] =  scan();
        }




        if (smlParamCount==1) // case of out
        {
            return (Instruction) ctor0.newInstance(label, Register.valueOf(smlScans[0]));
        }
        else if(smlParamCount==2)
        {
            if(ctr0ParamTypes[2].equals(RegisterName.class)) // add, sub, mul, div
                return (Instruction) ctor0.newInstance(label, Register.valueOf(smlScans[0]), Register.valueOf(smlScans[1]));

            if(ctr0ParamTypes[2].equals(Integer.class)) // mov
                return (Instruction) ctor0.newInstance(label, Register.valueOf(smlScans[0]), Integer.valueOf(smlScans[1]));

            if(ctr0ParamTypes[2].equals(String.class)) // jnz
                return (Instruction) ctor0.newInstance(label, Register.valueOf(smlScans[0]), String.valueOf(smlScans[1]));
        }
        else {
            return null;
        }





//        if (ctr0ParameterCount == 2)
//        {
//            System.out.println(2);
//            String r = scan();
//            String s = scan();
//            return (Instruction) ctr0.newInstance(label, Register.valueOf(r), Register.valueOf(s));
//        } else if (ctr0ParameterCount == 3) {
//            System.out.println(3);
//        }
//        ctr0.newInstance()
//
//        System.out.println(" --- ");
//        System.out.println(opcodeClassName + " has " + opcodeClassConstructors.length + " constructors");
//        for (Constructor ctr:opcodeClassConstructors) {
//            int ctrParameterCount = ctr.getParameterCount();
//            System.out.println(ctrParameterCount);
//        }
//        System.out.println(" --- ");


        /*
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case MulInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case OutInstruction.OP_CODE -> {
                String s = scan();
                return new OutInstruction(label, Register.valueOf(s));
            }
            case MovInstruction.OP_CODE -> {
                String r = scan();
                String x = scan();
                return new MovInstruction(label, Register.valueOf(r), Integer.valueOf(x));
            }
            case JnzInstruction.OP_CODE -> {
                String r = scan();
                String L = scan();
                return new JnzInstruction(label, Register.valueOf(r), String.valueOf(L));
            }

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        */

        /*
        return null;
        */
        return null;

    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}