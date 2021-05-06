import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeParser {
    static int num = 0;
    static FileOutputStream out = null;
    public static void setOutputStream(FileOutputStream oo) {
        out = oo;
    }
    private static void dfsParse(Node u, TreeNode father) {
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
        Node fc = u.getFirstChild();
        while (fc != null) {
            dfsParse(fc, ut);
            fc = fc.getNextSibling();
        }
        father.addSon(ut);
    }

    private static void writeNode(int index, int weight, List<Integer> sons) throws IOException {
        out.write(String.valueOf(index).getBytes()); out.write(' ');
        out.write(String.valueOf(weight).getBytes()); out.write(' ');
        if (sons != null)
        for (int p : sons) {
            out.write(String.valueOf(p).getBytes()); out.write(' ');
        }
        out.write('\n');
    }

    private static void dfsParseToFile(Node u, List<Integer> belong) throws IOException {
        if (u.getNodeType() == Node.COMMENT_NODE || u.getNodeType() == Node.TEXT_NODE || u.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
            num++;
            if (u.getNodeValue() == null) {//DOCUMENT_TYPE_NODE
                writeNode(num, 1, null);
            } else
                writeNode(num, (u.getNodeValue().length()+7)/8+1, null);
            belong.add(num);
            return;
        }

        List<Integer> s = new LinkedList<>();
        if (u.hasAttributes()) {
            NamedNodeMap t = u.getAttributes();
            for (int i = 0; i < t.getLength(); i++)
                dfsParseToFile(t.item(i), s);
        }

        Node fc = u.getFirstChild();
        while (fc != null) {
            try {
                dfsParseToFile(fc, s);
            }catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(num);
            }
            fc = fc.getNextSibling();
        }
        num ++;
        writeNode(num, 1, s);
        belong.add(num);
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

    public static void getRecordFile(InputStream inputSream) throws ParserConfigurationException, SAXException, IOException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputSream);
        NodeList nl = document.getChildNodes();
        List<Integer> s = new LinkedList<>();
        num = 0;
        for (int i = 0; i < nl.getLength(); i++) {
            dfsParseToFile(nl.item(i), s);
        }
        num++;
        writeNode(num, 1, s);
    }

}
