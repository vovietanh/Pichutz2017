import TextClassifier.DocumentCompare;

/**
 * Created by damir on 05/02/17.
 */
public class testClassifier {
    public static void main(String[] args){
        try {
        String f1 = "./src/test/DATA/alice.txt";
        String f2 = "./src/test/DATA/holmes.txt";
        DocumentCompare dc = new DocumentCompare();
        System.out.println(dc.compare(f1,f2));

//        f1 = "./Pichutz2017/src/test/DATA/alice_a.txt";
//        f2 = "./Pichutz2017/src/test/DATA/alice_b.txt";
//        System.out.println(dc.compare(f1,f2));
//
//        f1 = "./Pichutz2017/src/test/DATA/holmes_a.txt";
//        f2 = "./Pichutz2017/src/test/DATA/holmes_b.txt";
//        System.out.println(dc.compare(f1,f2));
//
//        f1 = "./Pichutz2017/src/test/DATA/holmes_a.txt";
//        f2 = "./Pichutz2017/src/test/DATA/alice_a.txt";
//        System.out.println(dc.compare(f1,f2));
//
//        f1 = "./Pichutz2017/src/test/DATA/holmes_b.txt";
//        f2 = "./Pichutz2017/src/test/DATA/alice_b.txt";
//        System.out.println(dc.compare(f1,f2));

        }catch (Exception e){e.printStackTrace();}
    }

}
