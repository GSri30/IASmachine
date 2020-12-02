import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IMT2019030_MAIN {
    public static void main(String[] args) 
    throws IOException {
        //Create a new IAS machine
        IASMachine IAS=new IASMachine();
        
        //Read assembly code from a text file
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        
        //Store it in Main memory of IAS machine
        try {
            String line;
            while ((line = br.readLine()) != null) {
                IAS.memory.m.set(Integer.parseInt(line.split(" ")[0],2),new BitsArray(line.split(" ")[1]));
            }
        } finally {
            br.close();
        }
        
        //Execute
        IAS.execute();
    }
}