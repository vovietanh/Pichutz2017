package TextClassifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//an incredibly fast classifier
public class DocumentCompare {

    public double compare(String fname1, String fname2) throws IOException {
        StringBuffer a = readFile(fname1);
        StringBuffer b = readFile(fname2);
        StringDistance dis = new StringDistance();
        return dis.getDistance(a,b);
    }

    private StringBuffer readFile(String fname1) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(fname1));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while((line=read.readLine())!=null){
            sb.append(line);
        }
        read.close();
        return sb;
    }


}
