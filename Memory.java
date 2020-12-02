import java.util.ArrayList;

class Memory {
    ArrayList<BitsArray>m;
    
    Memory(int size){
        this.m=new ArrayList<BitsArray>(size);
        for(int i=0;i<size;i++){
            this.m.add(new BitsArray(40,0));
        }
    }
}