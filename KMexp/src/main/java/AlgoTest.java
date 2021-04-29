
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AlgoTest {
    public TreeNode root;
    public int K = 352;
    public AlgoTest(TreeNode r) {
        root = r;
    }

    public int calcKM() {
        KM km = new KM(root, K);
        return km.getPartitionNumer();
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String name = "C:\\Users\\29069\\Desktop\\data\\";
        FileOutputStream out = new FileOutputStream(name+"xmark.txt",true);
        for (int s = 5; s <= 5 ; s++) {
            StringBuilder fileprefix = new StringBuilder(name+"xf");
            String filename = fileprefix.append(s).append(".xml").toString();
            System.out.println("opening "+filename);
            InputStream is = new FileInputStream(filename);
            TreeNode root = TreeNodeParser.getSigModRecord(is);
            Tree tree = new Tree(root);
            out.write(new String("file:" + filename).getBytes());
            out.write(new String(" total nodes:" + TreeNode.total).getBytes());
            System.out.println("total nodes:" + TreeNode.total);
            tree.postOrder();
            System.out.println("size:"+root.totalWeight);
            out.write(new String(" size:"+root.totalWeight).getBytes());
           // out.write('\n');
            AlgoTest test = new AlgoTest(root);
            //System.out.print("KM :");
            int ans = test.calcKM();
            System.out.println(ans);
            out.write(new String(" ans:"+String.valueOf(ans)).getBytes());
            out.write('\n');
        }
        out.close();
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