import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class TreeNodeParser {
    static int sum = 0;
    public static void dfsParse(Node u, TreeNode father) {
        //sum ++;
        TreeNode ut;

        if (u.getNodeType() == Node.COMMENT_NODE || u.getNodeType() == Node.TEXT_NODE || u.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
            if (u.getNodeValue() == null) {//DOCUMENT_TYPE_NODE
                ut = new TreeNode("typenode", 1);
                father.addSon(ut);
                return;
            }
            ut = new TreeNode(u.getNodeValue());
            father.addSon(ut);
            return;
        }
        ut = new TreeNode(u.getNodeName(), 1);
        if (u.hasAttributes()) {
            NamedNodeMap t = u.getAttributes();
            for (int i = 0; i < t.getLength(); i++) {
                TreeNode at = new TreeNode(t.item(i).getNodeName(), 1);
                TreeNode atson = new TreeNode(t.item(i).getFirstChild().getNodeValue());
                at.addSon(atson);
                ut.addSon(at);
            }
        }
        //NodeList nl = u.getChildNodes();
        //int tnl = nl.getLength();
        Node fc = u.getFirstChild();
        while (fc != null) {
            dfsParse(fc, ut);
            fc = fc.getNextSibling();
        }
        father.addSon(ut);
    }

    public static TreeNode getSigModRecord(InputStream inputSream) throws ParserConfigurationException, SAXException, IOException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputSream);
        NodeList nl = document.getChildNodes();
        TreeNode root = new TreeNode("root", 1);
        for (int i = 0; i < nl.getLength(); i++) {
            dfsParse(nl.item(i), root);
        }
        return root;
    }


   /* public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        InputStream is = new FileInputStream("SigmodRecord.xml");
        TreeNode root = getSigModRecord(is);
        Tree tree = new Tree(root);
        System.out.println(sum);
        System.out.println(tree.count(root));
    }*/
}
