import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class AlgoTest {
    public static TreeNode root;
    public static int K = 128;
    public AlgoTest(TreeNode r) {
        root = r;
    }

    public static int calcKM() {
        KM km = new KM(root, K);
        return km.getPartitionNumer();
    }

    public static void parseToFile(String filename) throws IOException, SAXException, ParserConfigurationException {
        InputStream in = new FileInputStream(filename);
        FileOutputStream out = new FileOutputStream(filename+".node",false);
        TreeNodeParser.setOutputStream(out);
        TreeNodeParser.getRecordFile(in);
        out.close();
        in.close();
    }

    public static void inmemoryParse(String filename) throws IOException, SAXException, ParserConfigurationException {
        InputStream in = new FileInputStream(filename);
        FileOutputStream out = new FileOutputStream(filename+"-xmark.result",true);

        System.out.println("opening "+filename);
        out.write(new String("file:" + filename).getBytes());
        TreeNode r = TreeNodeParser.getSigModRecord(in);
        root = r;

//        Tree tree = new Tree(root);
//        System.out.println("total nodes:" + TreeNode.total);
//        out.write(new String(" total nodes:" + TreeNode.total).getBytes());
//
//        tree.postOrder();
//        System.out.println("size:"+root.totalWeight);
//        out.write(new String(" size:"+root.totalWeight).getBytes());

        int ans = calcKM();
        System.out.println(ans);
        out.write(new String(" ans:"+String.valueOf(ans)).getBytes());
        out.write('\n');
        out.close();
        in.close();
    }

    public static void basememoryParse(String filename) throws IOException, SAXException, ParserConfigurationException {
        //TODO
        InputStream in = new FileInputStream(filename);
        FileOutputStream out = new FileOutputStream(filename+"-xmark.bsresult",true);

        System.out.println("opening "+filename);
        out.write(new String("file:" + filename).getBytes());
        //TreeNode r = TreeNodeParser.getSigModRecord(in);

        //  Tree tree = new Tree(root);
        //  System.out.println("total nodes:" + TreeNode.total);
        //  out.write(new String(" total nodes:" + TreeNode.total).getBytes());

        //  tree.postOrder();
        //  System.out.println("size:"+root.totalWeight);
        //  out.write(new String(" size:"+root.totalWeight).getBytes());
        //root = r;
        int ans = calcKM();
        System.out.println(ans);
        out.write(new String(" ans:"+String.valueOf(ans)).getBytes());
        out.write('\n');
        out.close();
    }


    public static void VaryKinmemoryParse(String filename,int num) throws IOException, SAXException, ParserConfigurationException {
        InputStream in = new FileInputStream(filename);
        FileOutputStream out = new FileOutputStream("VaryK.result",true);

        System.out.println("opening "+filename);
        out.write(String.valueOf(num).getBytes());
        TreeNode.total = 0;
        TreeNode r = TreeNodeParser.getSigModRecord(in);
        root = r;
        System.out.println("nodes:"+TreeNode.total);
        while (K != 128 * 512 * 2) {//K = 128 1k,  ... K = 128 * 512, 512k
            int ans = calcKM();
            System.out.println("K="+K+" ,"+ans);
            out.write(new String(" " + String.valueOf(ans)).getBytes());
            K = K * 2;
        }
        out.write('\n');
        out.close();
        in.close();
    }
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String name = "C:\\Users\\29069\\Desktop\\data\\";
        String name1 = "D:\\XMLdata\\";
        //name = "test.xml";
        //parseToFile(name);
        VaryKinmemoryParse(name+"xf5.xml", 5);

        /*for (int s = 2; s <= 10 ; s++) {
            String filename = name + "x" + s + "g.xml";
            parseToFile(filename);
        }*/
    }
}
/*
total nodes:42054
Algo77 :1158
EKM :291
RS :494
DFS :1434
KM :1506
//BFS :15264

seperate:

Algo77 :1158
EKM :291
RS :565
DFS :2677
KM :1506
//BFS :15264
GHDW: 263
DHW: 1098
 */