package EvaluationMap;

import TextClassifier.DocumentCompare;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by damir on 07/02/17.
 */
public class FileAdder {
    private String slash = java.io.File.separator;


    public void add(String fname, double value) throws IOException {
        String in = getFname(fname);
        //get all files from a TextFiles directory
        List<File> files = getFiles();
        //compare with each file into a map <oldFile, value>
        HashMap<String, Double> comparisons = getComparisonsMap(fname, files);
        //append the comparison into a evaluationHistory
        historyAppend(comparisons,in);
        //now update the evaluationMap's existing lines
        updateExistingLines(comparisons,value);
        //TODO now append the comparisons
        BufferedWriter write = new BufferedWriter(new FileWriter("./BRAIN/evaluationMap.csv",true));
        String wline = "";
        for(String key:comparisons.keySet()){
            double v = comparisons.get(key);
            wline=wline+v+",";
        }
        wline = wline+"["+value+"]|"+value+"_"+in;
        wline = finalFormat(wline);
        write.write(wline+"\n");
        write.close();

        //move the file
        FileUtils.copyFile(new File(fname), new File("./BRAIN/TextFiles/"+value+"_"+in));
    }

    private void updateExistingLines(HashMap<String, Double> comparisons, double value) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("./BRAIN/evaluationMap.csv"));
        File tmp = new File("./BRAIN/evaluationMap_tmp.csv");
        BufferedWriter write = new BufferedWriter(new FileWriter(tmp));
        String line="";
        while((line = read.readLine())!=null){
            String wline = ""; //line to write
            String[]spMain = line.split("\\|");
            //make an array to sort with new value
            String[]arrsp=spMain[0].split(",");
            if(arrsp.length==1){
                wline = wline+comparisons.get(spMain[1]);
                wline = wline+","+arrsp[0];
                wline = wline+"|"+spMain[1];
                write.write(wline+"\n");
            }
            else{
                double[]arr = new double[arrsp.length];
                for (int i = 0; i < arrsp.length-1; i++) {
                    arr[i]  = Double.parseDouble(arrsp[i]);
                }
                arr[arrsp.length-1] = comparisons.get(spMain[1]);
                Arrays.sort(arr);
                for(double d:arr){
                    wline = wline+","+d;
                }
                wline = wline+","+arrsp[arrsp.length-1]+"|"+spMain[1];
                wline = wline.substring(1,wline.length());
                wline = finalFormat(wline);
                write.write(wline+"\n");
            }
        }

        write.close();
        read.close();
        FileUtils.copyFile(tmp, new File("./BRAIN/evaluationMap.csv"));
        FileUtils.forceDelete(tmp);
    }


    private String finalFormat(String in){
      //  0.7147092934200221,0.7815604966697735,0.5739374092373368,[0.3]|0.3_alice_b.txt
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

    private List<File> getFiles() {
        return getFilesFrom("./BRAIN/TextFiles");
    }

    private List<File> getFilesFrom(String ffrom) {
        File dir = new File(ffrom);
        String[] extensions = new String[] { "txt"};
        return (List<File>) FileUtils.listFiles(dir, extensions, true);
    }


    private HashMap<String, Double> getComparisonsMap(String fname, List<File> files) throws IOException {
        HashMap<String, Double> comparisons = new HashMap<String, Double>();
        DocumentCompare dc = new DocumentCompare();
        for(File f:files){
            String fileName = f.getName();
            Double cmp = dc.compare(f.getAbsolutePath(), fname);
            comparisons.put(fileName,cmp);
        }
        return comparisons;
    }

    private void historyAppend(HashMap<String, Double> comparisons, String in) throws IOException {
        File f = new File("./BRAIN/evaluationHistory");
        BufferedWriter write = new BufferedWriter(new FileWriter(f,f.exists()));
        for(String key:comparisons.keySet()){
            write.write(key+":"+in+":"+comparisons.get(key)+",");
        }
        write.close();
    }


    //WARNING, THIS WILL REMOVE ALL PREVIOUS BRAIN FILES, AND SHOULD BE USED ONLY AT EACH NEW INSTANCE
    public void addFirst(String fname, double value) throws Exception {
        File name = new File(fname);
        if(!name.exists()){throw new Exception();}

        cleanTextFiles(); //YOU HAVE BEEN WARNED!!!
        String copyTo = getFname(fname);
        String copyToPath = "BRAIN"+slash+"TextFiles"+slash+value+"_"+copyTo;
        FileUtils.copyFile(name, new File(copyToPath));
        //now, write a new evaluationMap
        newEvaluationMap(value, copyTo);

    }

    //the evaluation map consists of compare1, compare2,...[uservale]|TheFileName.txt
    private void newEvaluationMap(double value, String copyTo) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter("BRAIN/evaluationMap.csv"));
        write.write("["+value+"]|"+value+"_"+copyTo+"\n");
        write.close();
    }

    private void cleanTextFiles() throws IOException {
        File dir = new File("./BRAIN/TextFiles/");
        FileUtils.cleanDirectory(new File("./BRAIN/"));
        if(!dir.exists()){dir.mkdirs();}
        else{
            FileUtils.cleanDirectory(dir);
        }
    }

    private String getFname(String copyTo) {
        if(copyTo.contains(slash)){
            copyTo = copyTo.substring(copyTo.lastIndexOf(slash)+1,copyTo.length());
        }
        return copyTo;
    }

}
