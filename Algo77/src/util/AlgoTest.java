package util;

import algo06.*;
import algo77.Algo77;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AlgoTest {
    public TreeNode root;
    public int K = 352;
    public AlgoTest(TreeNode r) {
        root = r;
    }

    public int calcAlgo77() {
        Algo77 al77 = new Algo77(root, K);
        al77.dfs(root);
        return al77.ans + 1;
    }

    public int calcEKM() {
        EKM ekm = new EKM(root, K);
        return ekm.getPartitionNumber();
    }

    public int calcKM() {
        KM km = new KM(root, K);
        return km.getPartitionNumer();
    }

    public int calcBFS() {
        PUREBFS bfs = new PUREBFS(root, K);
        return bfs.getPartitionNumber();
    }

    public int calcDFS() {
        PUREDFS dfs = new PUREDFS(root, K);
        return dfs.getPartitionNumber();
    }

    public int calcRS() {
        RS rs = new RS(root, K);
        return rs.getPartitionNumber();
    }

    public int calcGHDW() {
        GHDW g = new GHDW(root, K);
        return g.ans;
    }

    public int calcDHW() {
        DHW g = new DHW(root, K);
        g.getPartition();
        return g.ans;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        InputStream is = new FileInputStream("SigmodRecord.xml");
        TreeNode root = TreeNodeParser.getSigModRecord(is);
        Tree tree = new Tree(root);
        System.out.println("total nodes:" + tree.count(root));
        AlgoTest test = new AlgoTest(root);

//        System.out.print("Algo77 :");
//        System.out.println(test.calcAlgo77());

//        System.out.print("EKM :");
//        System.out.println(test.calcEKM());

//        System.out.print("RS :");
//        System.out.println(test.calcRS());

//        System.out.print("DFS :");
//        System.out.println(test.calcDFS());

//        System.out.print("KM :");
//        System.out.println(test.calcKM());

//        System.out.print("BFS :");
//        System.out.println(test.calcBFS());

//        System.out.print("GHDW :");
//        System.out.println(test.calcGHDW());

        System.out.print("DHW :");
        System.out.println(test.calcDHW());
        return;
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