import EvaluationMap.FileAdder;

/**
 * Created by damir on 07/02/17.
 */
public class testEvalMap {
    public static void main(String[] args){
        FileAdder fa = new FileAdder();
        //adds a first file
        try {
            fa.addFirst("./src/test/DATA/holmes.txt",0.5);
            fa.add("./src/test/DATA/alice.txt",0.1);
            fa.add("./src/test/DATA/alice_a.txt",0.2);
            fa.add("./src/test/DATA/alice_b.txt",0.3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
