class BitsArray{
    private String bits;
    BitsArray(int numBits,int value){
        this.bits=String.format("%"+numBits+"s",Integer.toBinaryString(value)).replaceAll(" ","0");
    }
    BitsArray(String part){
        this.bits=part;
    }
    String getBits(){
        return bits;
    }
    int getValue(){
        return Integer.parseInt(this.bits,2);
    }
    BitsArray getRange(int startingInd,int endingInd){
        return new BitsArray(this.bits.substring(startingInd, endingInd));
    }
    BitsArray setRange(int startingInd,int endingInd,BitsArray bitStream){
        return new BitsArray(this.getBits().substring(0, startingInd)+bitStream.getBits()+this.getBits().substring(endingInd, 40));
    }
    boolean isEmpty(){
        return String.format("%"+this.bits.length()+"d",0).replaceAll(" ","0").equals(this.bits);
    }
}


class MixedReturn{
    Runnable runnable;int interger;
    MixedReturn(int i,Runnable r){
        this.interger=i;
        this.runnable=r;
    }
}   