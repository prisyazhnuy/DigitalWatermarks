package digitalwatermarks;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Picture {
    private int height;
    private int width;
    private int[] R;
    private int[] G;
    private int[] B;
    private int[] CodeColor;
    //int[][] pixelData;// = new int[img.getHeight() * img.getWidth()][3];
    
    private static int[] getPixelData(BufferedImage img, int x, int y) {
        int argb = img.getRGB(x, y);
        int rgb[] = new int[] {
                                (argb >> 16) & 0xff, //red
                                (argb >>  8) & 0xff, //green
                                (argb      ) & 0xff  //blue
                              };
        //System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]);
        return rgb;
    }
    
    private BufferedImage show(int[] red, int[] green, int[] blue){
        int[] pixels = new int[height*width*3];
        for(int i=0; i<height*width; i++){
            pixels[i*3]=red[i];
            pixels[i*3+1]=green[i];
            pixels[i*3+2]=blue[i];
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }
    
    public void loadFromFile(BufferedImage bufferedImage){
        bufferedImage = net.sf.image4j.util.ConvertUtil.convert24(bufferedImage);
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        //pixelData = new int[height*width][3];
        R = new int[height*width];
        G = new int[height*width];
        B = new int[height*width];
        int[] rgb;
        int counter = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                rgb = getPixelData(bufferedImage, j, i);
                R[counter]=rgb[0];
                G[counter]=rgb[1];
                B[counter]=rgb[2];
                //System.arraycopy(rgb, 0, pixelData[counter], 0, rgb.length);
                counter++;
            }
        }
    }
    
    private int[] codeLSB(int[] color, String mess){
        String start = "п0ч@т0к";
        String finish = "кiнeu,b";
        String message = start+mess+finish;
        //System.out.println(message);
        int[] result =new int[color.length];
        ArrayList<String> colorList = new ArrayList<String>();
        char[] messList = Functions.stringToBinary(message).toCharArray();
        for(int c : color){
            colorList.add(Functions.intToBinary(c));
        }
        for(int i=0; i<message.length(); i++){
            char[] ch = colorList.get(i).toCharArray();
            //ch[ch.length-1]=messList[i];
            ch[0]=messList[i];
            colorList.set(i, new String(ch));
        }
        for(int i=0; i<color.length; i++){
            result[i]=Functions.binaryToInt(colorList.get(i)); //Integer.parseInt(colorList.get(i));
        }
        CodeColor =new int[result.length];
        System.arraycopy(result, 0, CodeColor, 0, result.length);
        return result;
    }
    
    public String decodeLSB(){
        ArrayList<String> colorList = Functions.intToBinary(CodeColor);
        String LSB = "";
        for(String color : colorList){
            char[] ch = color.toCharArray();
            LSB+=ch[ch.length-1];
        }
        //System.out.println(LSB);
        int start = LSB.indexOf(Functions.stringToBinary("п0ч@т0к"));
        System.out.println(start);
        int finish = LSB.indexOf(Functions.stringToBinary("кiнeu,b"));
        System.out.println(finish);
        String message = "";
        //char[] ch = LSB.toCharArray();
        for(int i=start; i<finish; i+=8){
            message+=Functions.binaryToInt(LSB.substring(i, i+8));
        }
        return message;
    }
    
    public BufferedImage showImageStandart(){
        return show(R, G, B);
    }
    
    public BufferedImage showImageCode(String mess){
        return show(R, G, codeLSB(B, mess));
    }
}
