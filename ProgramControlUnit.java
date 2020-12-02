import java.util.HashMap;

class ProgramControlUnit {
    
    ISA ISA;
    Registers registers;
    ALU ALU;
    Memory memory;
    
    ProgramControlUnit(ALU ALU,Memory memory){
        this.ISA=new ISA();
        this.registers=new Registers();
        this.ALU=ALU;
        this.memory=memory;
    }
    
    class Registers{
        int PC;
        BitsArray MAR,IR,IBR;
        Registers(){    
            this.PC=0;
            this.MAR=new BitsArray(12,0);
            this.IR=new BitsArray(8,0);
            this.IBR=new BitsArray(20,0);
        }
    }
    
    class ISA{
        HashMap<String,Runnable>opcodes;
        ISA(){
            opcodes=new HashMap<String,Runnable>();
            //LOAD_MQ
            opcodes.put("00001010", ()->{
                        ALU.registers.AC=ALU.registers.MQ;});
            //LOAD_MQ_MX
            opcodes.put("00001001", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.MQ=ALU.registers.MBR;});
            //STOR_MX
            opcodes.put("00100001", ()->{
                        memory.m.set(registers.MAR.getValue(), ALU.registers.AC);});
            //LOAD_MX
            opcodes.put("00000001", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.registers.MBR;});
            //LOAD_negMX()
            opcodes.put("00000010", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.getNeg(ALU.registers.MBR);});
            //LOAD_modMX
            opcodes.put("00000011", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.getNeg(ALU.getMOD(ALU.registers.MBR));});
            //LOAD_negModMX
            opcodes.put("00000100", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.getNeg(ALU.getMOD(ALU.registers.MBR));});
            //JUMP_MXleft
            opcodes.put("00001101", ()->{
                        registers.PC=registers.MAR.getValue();
                        registers.IBR=new BitsArray(20,0);});
            //JUMP_MXright
            opcodes.put("00001110", ()->{
                        registers.PC=registers.MAR.getValue();
                        registers.IBR=memory.m.get(registers.MAR.getValue()).getRange(20, 40);});
            //condJUMP_MXleft
            opcodes.put("00001111", ()->{
                        if(ALU.registers.AC.getValue()>=0){
                            registers.PC=registers.MAR.getValue();
                            registers.IBR=new BitsArray(20,0);
                        }});
            //condJUMP_MXright
            opcodes.put("00010000", ()->{
                        if(ALU.registers.AC.getValue()>=0){
                            registers.PC=registers.MAR.getValue();
                            registers.IBR=memory.m.get(registers.MAR.getValue()).getRange(20, 40);
                        }});
            //ADD_MX
            opcodes.put("00000101", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.add(ALU.registers.AC,ALU.registers.MBR);});
            //ADD_modMX
            opcodes.put("00000111", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.add(ALU.registers.AC,ALU.getMOD(ALU.registers.MBR));});
            //SUB_MX
            opcodes.put("00000110", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.sub(ALU.registers.AC,ALU.registers.MBR);});
            //SUB_modMX
            opcodes.put("00001000", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        ALU.registers.AC=ALU.sub(ALU.registers.AC,ALU.getMOD(ALU.registers.MBR));});
            //MUL_MX
            opcodes.put("00001011", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        BitsArray multiplication=ALU.mul(ALU.registers.MBR, ALU.registers.MQ);
                        ALU.registers.AC=multiplication.getRange(0, 40);
                        ALU.registers.MQ=multiplication.getRange(0, 80);});
            //DIV_MX
            opcodes.put("00001100", ()->{
                        ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
                        BitsArray[]result=ALU.div(ALU.registers.AC,ALU.registers.MBR);
                        ALU.registers.MQ=result[0];
                        ALU.registers.AC=result[1];});
            //LSH
            opcodes.put("00010100", ()->{
                        ALU.registers.AC=ALU.LSH(ALU.registers.AC);});
            //RSH
            opcodes.put("00010101", ()->{
                        ALU.registers.AC=ALU.RSH(ALU.registers.AC);});
            //STOR_MXleft
            opcodes.put("00010010", ()->{
                        memory.m.set(registers.MAR.getValue(), memory.m.get(registers.MAR.getValue()).setRange(8, 20, ALU.registers.AC.getRange(28, 40)));});
            //STOR_MXright
            opcodes.put("00010011", ()->{
                        memory.m.set(registers.MAR.getValue(), memory.m.get(registers.MAR.getValue()).setRange(28, 40, ALU.registers.AC.getRange(28, 40)));}); 
            //HALT
            //opcodes.put("00000000", HALT());       
        }
    }
    
    
    MixedReturn decode(){
        //HALT
        if(registers.IR.isEmpty()){
            return new MixedReturn(0,null);
        }
        //Opcode not found
        else if(ISA.opcodes.get(registers.IR.getBits())==null){
            return new MixedReturn(-1,null);
        }        
        return new MixedReturn(1, ISA.opcodes.get(registers.IR.getBits()));
    }
    
    
    void fetch(){
        if(!registers.IBR.isEmpty()){
            registers.IR=registers.IBR.getRange(0,8);
            registers.MAR=registers.IBR.getRange(8,20);
            registers.IBR=new BitsArray(20,0);
            registers.PC++;
        }
        else{
            registers.MAR=new BitsArray(12,registers.PC);
            ALU.registers.MBR=memory.m.get(registers.MAR.getValue());
            if(!ALU.registers.MBR.getRange(0,20).isEmpty()){
                registers.IR=ALU.registers.MBR.getRange(0,8);
                registers.MAR=ALU.registers.MBR.getRange(8,20);
                registers.IBR=ALU.registers.MBR.getRange(20,40);
            }
            else{
                registers.IR=ALU.registers.MBR.getRange(20,28);
                registers.MAR=ALU.registers.MBR.getRange(28,40);
                registers.PC++;
            }
        }
    }
}