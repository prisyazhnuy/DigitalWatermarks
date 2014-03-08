package digitalwatermarks;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Picture {
    private int height;
    private int width;
    private int[] R;
    private int[] G;
    private int[] B;
    int[][] pixelData;// = new int[img.getHeight() * img.getWidth()][3];
    
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
    
    public void loadFromFile(BufferedImage bufferedImage){
        bufferedImage = net.sf.image4j.util.ConvertUtil.convert24(bufferedImage);
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        pixelData = new int[height*width][3];
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
                System.arraycopy(rgb, 0, pixelData[counter], 0, rgb.length);
                counter++;
            }
        }
    }
    
    public BufferedImage showImage(){
        int[] pixels = new int[height*width*3];
        for(int i=0; i<height*width; i++){
            pixels[i*3]=R[i];
            pixels[i*3+1]=G[i];
            pixels[i*3+2]=B[i];
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }
}
