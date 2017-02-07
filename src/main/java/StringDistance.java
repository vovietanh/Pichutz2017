import org.apache.commons.math3.util.MathArrays;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by damir on 05/02/17.
 */
public class StringDistance {


    public void printLine(String aa, String bb, double r) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/damir/Desktop/openai/eval.csv",true));
        String cc = aa+bb;

        double abyte = getZipSize(aa);
        double bbyte = getZipSize(bb);
        double cbyte = getZipSize(cc);


        final byte[] autf = aa.getBytes("UTF-8");
        final byte[] butf = bb.getBytes("UTF-8");
        double ao = (double)autf.length;
        double bo = (double)butf.length;
        double sum = ao+bo;



        double a = abyte/cbyte;
        double b = bbyte/cbyte;

        double a1 = abyte/sum;
        double b1 = bbyte/sum;

        double a2 = ao/cbyte;
        double b2 = bo/cbyte;

        double a3 = ao/sum;
        double b3 = bo/sum;

        double extra = cbyte/sum;


        System.out.println(a+","+b+","+a1+","+b1+","+a2+","+b2+","+a3+","+b3+","+extra+","+r);
        bw.write(a+","+b+","+a1+","+b1+","+a2+","+b2+","+a3+","+b3+","+extra+","+r+"\n");
        bw.close();
    }

 //TODO make a method for comparing two StringBuffers


    private double getZipSize(String append) throws IOException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ZipOutputStream zos = new ZipOutputStream(baos);
  /* File is not on the disk, test.txt indicates
     only the file name to be put into the zip */
            ZipEntry entry = new ZipEntry("test.txt");

            zos.putNextEntry(entry);
            zos.write(append.getBytes());
            zos.closeEntry();

  /* use more Entries to add more files
     and use closeEntry() to close each file entry */

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return baos.size();
    }

    public double cosAngle(final double[] x, final double[] y){return MathArrays.cosAngle(x,y);}


}
