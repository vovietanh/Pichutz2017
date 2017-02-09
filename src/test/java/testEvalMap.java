import EvaluationMap.FileAdder;
import EvaluationMap.FileRemover;

/**
 * Created by damir on 07/02/17.
 */
public class testEvalMap {
    public static void main(String[] args){
        FileAdder fa = new FileAdder();
        //adds a first file
        try {
//            fa.addFirst("./src/test/DATA/sfsdf.txt",0.5);
            fa.addFirst("./src/test/DATA/holmes.txt",0.5);
            fa.add("./src/test/DATA/alice.txt",0.1);
            fa.add("./src/test/DATA/alice_a.txt",0.2);
            fa.add("./src/test/DATA/alice_b.txt",0.3);

            FileRemover fr = new FileRemover();
            fr.removeFile("./src/test/DATA/holmes.txt");
            fr.removeFile("./src/test/DATA/alice.txt");
            fr.removeFile("./src/test/DATA/alice_a.txt");
            fr.removeFile("./src/test/DATA/alice_b.txt");
//            fr.removeFile("./src/test/DATA/DNE.txt");




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
