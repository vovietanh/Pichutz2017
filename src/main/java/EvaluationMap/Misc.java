package EvaluationMap;

import java.util.Arrays;

/**
 * Created by damir on 08/02/17.
 */
public class Misc {
    private String slash = java.io.File.separator;

    public String getFname(String copyTo) {
        if(copyTo.contains(slash)){
            copyTo = copyTo.substring(copyTo.lastIndexOf(slash)+1,copyTo.length());
        }
        return copyTo;
    }


    public String finalFormat(String in){
        String[] sp = in.split("\\|");
        String[] n = sp[0].split(",");
        double[]d = new double[n.length-1];
        for(int t=0;t<n.length-1;t++){
            d[t]=Double.parseDouble(n[t]);
        }
        Arrays.sort(d);
        String ret = "";
        for(double a:d){ret = ret+","+a;}
        ret=ret+","+n[n.length-1];
        ret=ret+"|"+sp[1];
        ret=ret.substring(1,ret.length());
        return ret;
    }

}
