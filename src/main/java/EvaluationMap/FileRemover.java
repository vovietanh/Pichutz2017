package EvaluationMap;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;

public class FileRemover {

    public void removeFile(String fname) throws Exception {
        String in = new Misc().getFname(fname);
        //make a hashmap of the history
        HashMap<String, Double> history = parseHistory(in);
        //with this history, filter the evaluation map
        parseEvaluationMap(in, history);
        //finally, remove txt from a brain
        File f = new File("./BRAIN/TextFiles/"+in);
        if(!f.exists()){throw new Exception();}
        FileUtils.forceDelete(f);

    }

    private void parseEvaluationMap(String in, HashMap<String, Double> history) throws IOException {
        File dest = new File("./BRAIN/evaluationMap.csv");
        File source = new File("./BRAIN/evaluationMap_temp.csv");
        BufferedReader read = new BufferedReader(new FileReader(dest));
        BufferedWriter write = new BufferedWriter(new FileWriter(source));
        String line="";
        while((line=read.readLine())!=null){
            String[]sp = line.split("\\|");
            if (!sp[1].contains(in)){
                String key1 = sp[1]+":"+in;
                String key2 = in+":"+sp[1];
                Double val = history.get(key1);
                if(val==null){val = history.get(key2);}
                String valstr = val.toString();
               line = line.replace(valstr,"");
               while(line.contains(",,")){line = line.replaceAll(",,",",");}
               if(line.startsWith(",")){line=line.substring(1,line.length());}


               line = new Misc().finalFormat(line);
               write.write(line+"\n");
            }

        }
        read.close();
        write.close();
        FileUtils.copyFile(source,dest);
        FileUtils.forceDelete(source);
    }

    private HashMap<String, Double> parseHistory(String in) throws IOException {
        HashMap<String, Double> history = new HashMap<String, Double>();
        File dest = new File("./BRAIN/evaluationHistory");
        File source = new File("./BRAIN/evaluationHistory_temp");
        BufferedReader read = new BufferedReader(new FileReader(dest));
        BufferedWriter write = new BufferedWriter(new FileWriter(source));
        String line="";
        while((line=read.readLine())!=null){
            String[] sp = line.split(",");
            for(String str:sp){
                String[]s=str.split(":");
                String compares = s[0]+":"+s[1];
                double d = Double.parseDouble(s[2]);
                history.put(compares,d);
                if(!s[0].contains(in)||!s[1].contains(in)){
                    write.write(s[0]+":"+s[1]+":"+s[2]+",");
                }
            }
        }
        read.close();
        write.close();
        FileUtils.copyFile(source,dest);
        FileUtils.forceDelete(source);
        return history;
    }

}
