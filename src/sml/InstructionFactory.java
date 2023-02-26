package sml;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class InstructionFactory {

    @Autowired
    private Map<String, Instruction> opcodeInstructionMap;


    public InstructionFactory()
    {
        var factory = new ClassPathXmlApplicationContext("/beans.xml");
        List<Instruction> listOfInstructions = (List<Instruction>) factory.getBean("myList");
        opcodeInstructionMap = listOfInstructions.stream()
                .collect(Collectors.toMap(Instruction::getOpcode, Function.identity()));
    }


    public Instruction getInstructionInstanceFromOpcode(String instructionOpcode)
    {
        Instruction instruction = opcodeInstructionMap.get(instructionOpcode);
        return instruction;
    }


    public void setOpcodeInstructionMap(Map opcodeInstructionMap) {
    }
}
