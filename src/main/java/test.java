import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by damir on 05/02/17.
 */
public class test {
    public static void main(String[] args){
        try { BufferedWriter bw = new BufferedWriter(new FileWriter("/home/damir/Desktop/openai/eval.csv"));
        bw.close();
        } catch (IOException e) {}

        Random rnd = new Random();
        String a = "1234567890qwertyuiopasdfghjklzxcbvnm-=[];',./? ";
        StringDistance sd = new StringDistance();
        ArrayList<Integer> mem = new ArrayList<Integer>();

    //for(int tt=0;tt<1000;tt++) {
    while(true){
    int wl = rnd.nextInt(1000)+1;
    String aa = "";
    for (int t = 0; t < wl; t++) {
        aa = aa + a.charAt(rnd.nextInt(a.length()));
    }

    wl = rnd.nextInt(1000)+1;
    String bb = "";
    for (int t = 0; t < wl; t++) {
        bb = bb + a.charAt(rnd.nextInt(a.length()));
    }


    StringMetric metric = StringMetrics.jaroWinkler();
    double result = metric.compare(aa, bb);
    int check = (int)(result*100);
    if(!mem.contains(check))
    {
    try {
        sd.printLine(aa, bb, result);
    } catch (IOException e) {
        e.printStackTrace();
    }
    mem.add(check);

    }

    if(mem.size()>58){
    mem = new ArrayList<Integer>();
    }


}

    }

}
