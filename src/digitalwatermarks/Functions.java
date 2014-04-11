package digitalwatermarks;

import java.util.ArrayList;

public class Functions {
    private static byte[] stringToASCII(String mess){
        byte[] result = new byte[mess.length()];
        char[] messChar = mess.toCharArray();
        for(int i=0; i<mess.length(); i++){
            result[i]=(byte) messChar[i];
        }
        return result;
    }
    
    private static String asciiToBinary(byte bt){
         StringBuilder binary = new StringBuilder();
         int val = bt;  
         for (int i = 0; i < 8; i++)  
         {
             binary.append((val & 128) == 0 ? 0 : 1);  
             val <<= 1;  
         }
         return binary.toString();  
    }
    
    private static String asciiToBinary(byte[] bytes){
          StringBuilder binary = new StringBuilder();  
          for (byte b : bytes)  
          {
              binary.append(asciiToBinary(b));
          }  
          return binary.toString();  
    }
    
    public static String intToBinary(Integer i){
        return asciiToBinary(i.byteValue());
    }
    
    public static ArrayList<String> intToBinary(int[] input){
        ArrayList<String> result = new ArrayList<>();
        for(Integer i : input){
            result.add(intToBinary(i));
        }
        return result;
    }
    
    public static String stringToBinary(String mess){
        return asciiToBinary(stringToASCII(mess));
    }
    
    public static int binaryToInt(String bin){
        int sum=0;
        char[] binList = bin.toCharArray();
        for(int i=0; i<binList.length; i++){
            if(binList[binList.length-i-1]=='1'){
                sum+=Math.pow(2, i);
            }
            
        }
        return sum;
    }
}
