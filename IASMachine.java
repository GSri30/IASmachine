class IASMachine{
    
    Memory memory;
    ALU ALU;
    ProgramControlUnit programControlUnit;
    
    IASMachine(){
        this.memory=new Memory(1000);
        this.ALU=new ALU();
        this.programControlUnit=new ProgramControlUnit(ALU,memory);
    }
    
    void execute(){
        while(true){
            this.programControlUnit.fetch();
            
            System.out.println(Colors.ANSI_GREEN+"Fetched Data From The Memory Successfully!"+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE+"PC:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.PC+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE+"MAR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.MAR.getBits()+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE+"MBR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.MBR.getBits()+Colors.ANSI_RESET);
            // System.out.println(Colors.ANSI_BLUE+"IR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.IR.getBits()+Colors.ANSI_RESET);
            // System.out.println(Colors.ANSI_BLUE+"IBR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.IBR.getBits()+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE+"AC:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.AC.getBits()+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE+"MQ:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.MQ.getBits()+Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_YELLOW+String.format("%50s"," ").replaceAll(" ","-")+Colors.ANSI_RESET);
            
            if(this.programControlUnit.decode().interger==0){
                System.out.println(Colors.ANSI_RED+"Halted Program"+Colors.ANSI_RESET);
                break;
            }
            if(this.programControlUnit.decode().interger==-1){
                System.out.println(Colors.ANSI_RED+"Encountered wrong opcode!"+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_RED+"Process Terminated!"+Colors.ANSI_RESET);
                break;
            }
            else{
                
                this.programControlUnit.decode().runnable.run();
                
                System.out.println(Colors.ANSI_GREEN+"Decoded The Data Successfully!"+Colors.ANSI_RESET); 
                System.out.println(Colors.ANSI_BLUE+"PC:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.PC+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE+"MAR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.MAR.getBits()+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE+"MBR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.MBR.getBits()+Colors.ANSI_RESET);
                // System.out.println(Colors.ANSI_BLUE+"IR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.IR.getBits()+Colors.ANSI_RESET);
                // System.out.println(Colors.ANSI_BLUE+"IBR:  "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.programControlUnit.registers.IBR.getBits()+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE+"AC:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.AC.getBits()+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE+"MQ:   "+Colors.ANSI_RESET+Colors.ANSI_WHITE+this.ALU.registers.MQ.getBits()+Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_YELLOW+String.format("%50s"," ").replaceAll(" ","-")+Colors.ANSI_RESET);
            }
        }
    }
}