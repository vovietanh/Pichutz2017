package TextClassifier;

import org.apache.commons.math3.analysis.function.Asinh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by damir on 05/02/17.
 */
public class StringDistance {

//This is a fast-classifier that compares two StringBuffers. Although the values that come out are not 100% properly classified, it helps the AI component do the predictions.
//It is accurate, but not precise, nevertheless, it is good enough

    public double getDistance(StringBuffer sba, StringBuffer sbb) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(sba);
        sb.append(sbb);

        double abyte = getZipSize(sba.toString());
        double bbyte = getZipSize(sbb.toString());
        double cbyte = getZipSize(sb.toString());

        final byte[] autf = sba.toString().getBytes("UTF-8");
        final byte[] butf = sbb.toString().getBytes("UTF-8");
        double ao = (double)autf.length;
        double bo = (double)butf.length;
        double sum = ao+bo;

        double A = abyte/cbyte; //A-
        double B = bbyte/cbyte; //B-
//        double C = abyte/sum; //C
//        double D = bbyte/sum; //D
//        double E = ao/cbyte; //E
        double F = bo/cbyte; //F-
//        double G = ao/sum;//G
        double H = bo/sum;//H-
//        double extra = cbyte/sum;//I

        double J = cos(A*B*logistic(cos(A + F)*greater(H*atan2(A + F, less_or_equal(A, A)), F))
                + max(max(A, asinh(greater(H*cos(A + F), F*atan2(A + F, less_or_equal(A, A))))), B));
        return J;
    }


    private double cos(double x){return Math.cos(x);}
    private double max(double x, double y){return Math.max(x,y);}
    private double atan2(double x, double y){return Math.atan2(x,y);}
    private double asinh(double x){return new Asinh().value(x);}
    private double logistic(double x){return 1/(1+ Math.exp(-x));}
    private double greater(double x, double y){return x>y?1:0;}
    private double less_or_equal(double x, double y){return x<=y?1:0;}

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



}
