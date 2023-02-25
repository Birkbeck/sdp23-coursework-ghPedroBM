package sml;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


//@Service
public class InstructionFactory {
    private final Map<String, Instruction> opcodeInstructionMap;

    @Autowired
    public InstructionFactory(List<Instruction> instructions)
    {
        opcodeInstructionMap = instructions.stream()
                .collect(Collectors.toMap(Instruction::getOpcode, Function.identity()));
    }

    public Instruction getInstructionInstanceFromOpcode(String instructionOpcode)
    {
        Instruction instruction = opcodeInstructionMap.get(instructionOpcode);
        return instruction;
    }

}
