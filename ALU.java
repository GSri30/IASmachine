class ALU{
    
    ALU(){
        this.registers=new Registers();
    }
    
    Registers registers;
    class Registers{
        BitsArray MBR,AC,MQ;
        Registers(){
            this.MBR=new BitsArray(40,0);
            this.AC=new BitsArray(40,0);
            this.MQ=new BitsArray(40,0);
        }
    }
    
    BitsArray getNeg(BitsArray binaryString){
        return new BitsArray(40,-1*binaryString.getValue());
    }
    BitsArray getMOD(BitsArray binaryString){
        return new BitsArray(40,Math.abs(binaryString.getValue()));
    }
    BitsArray add(BitsArray num1,BitsArray num2){
        return new BitsArray(40, num1.getValue()+num2.getValue());
    }
    BitsArray sub(BitsArray num1,BitsArray num2){
        return new BitsArray(40,num1.getValue()-num2.getValue());
    }
    BitsArray mul(BitsArray num1,BitsArray num2){
        return new BitsArray(80,num1.getValue()*num2.getValue());
    }
    BitsArray[] div(BitsArray num1,BitsArray num2){
        BitsArray[]result= new BitsArray[2];
        result[0]=new BitsArray(40,num1.getValue()/num2.getValue());
        result[1]=new BitsArray(40,num1.getValue()%num2.getValue());
        return result;
    } 
    BitsArray LSH(BitsArray bitStream){
        return new BitsArray(40,2*bitStream.getValue());
    }
    BitsArray RSH(BitsArray bitStream){
        return new BitsArray(40,bitStream.getValue()/2);
    }
}
