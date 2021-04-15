package util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TreeNodeParser {
    static int sum = 0;
    /*public static void dfs(Node u) {
        //sum ++;
     //   Node.
       // Node.
      //  Node.DOCUMENT_TYPE_NODE
      //  System.out.println(u.getNodeType() + ":"+u.getNodeValue());
        if (u.hasAttributes()) sum += 2*u.getAttributes().getLength();
            //u.getAttributes()
        if (u.getNodeType() == Node.ELEMENT_NODE) {
            sum ++;
            NodeList nl = u.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++)
                dfs(nl.item(i));
//        } else if (u.getNodeType() == Node.ATTRIBUTE_NODE) {
//            sum ++;
//            System.out.print(u.getNodeName());
//            NodeList nl = u.getChildNodes();
//            for (int i = 0; i < nl.getLength(); i++)
//                dfs(nl.item(i));
        } else if (u.getNodeType() == Node.COMMENT_NODE) {
            sum ++;
        } else if (u.getNodeType() == Node.TEXT_NODE) {
            sum ++;
        } else System.out.println("undetected node type: " + u.getNodeType());
       /* if (element.hasAttributes()) {
            sum += element.getAttributes().getLength() * 2;
            //System.out.print("!");
        }
        if (element.hasChildNodes()) {
            NodeList nl = element.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    dfs((Element) nl.item(i));
                //} else if (nl.item(i).getNodeType() == Node.ATTRIBUTE_NODE) {
                //    dfs((Element) nl.item(i));
                } else if (nl.item(i).getNodeType() == Node.TEXT_NODE) {
                    sum++;
                } else System.out.println("undetected node type: " + nl.item(i).getNodeType());
            }
        }
    }*/

    public static void dfsParse(Node u, TreeNode father) {
        TreeNode ut;

        if (u.getNodeType() == Node.COMMENT_NODE || u.getNodeType() == Node.TEXT_NODE || u.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
            if (u.getNodeValue() == null) {
                System.out.println(u.getNodeType());
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
        NodeList nl = u.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            dfsParse(nl.item(i), ut);
        }
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

/*

        NodeList issueNodelist = element.getElementsByTagName("issue");//所有的book 节点
        TreeNode root = new TreeNode("SigmodRecord",1);
        //List<Issue> issues = new ArrayList<>();
        for(int i=0;i<issueNodelist.getLength();i++) {
            TreeNode issue = new TreeNode("issue",1);
            //Issue issue = new Issue();
            // Book book = new Book();
            Element issueElement = (Element)issueNodelist.item(i);//这个item() 函数返回的类型DeferredElementImpl，是Element 接口的实现类，Element 实现了Node，<span style="color:#ff0000;">如图1</span>，这个函数本身返回的是Node 接口类型
            NodeList issueChildNodelist = issueElement.getChildNodes();
            for(int j = 0;j < issueChildNodelist.getLength();j++) {
                if(issueChildNodelist.item(j).getNodeType() == Node.ELEMENT_NODE) { //看看这里面有什么属性node（有TEXT_NODE ，即那些空白，<span style="color:#ff0000;">如图2</span>）
                    if("volume".equals(issueChildNodelist.item(j).getNodeName())) {
                        TreeNode volume = new TreeNode("volume", 1);
                        TreeNode vo = new TreeNode(issueChildNodelist.item(j).getFirstChild().getNodeValue());
                        volume.addSon(vo);
                        issue.addSon(volume);
                        //issue.volume = issueChildNodelist.item(j).getFirstChild().getNodeValue();
                    } else if("number".equals(issueChildNodelist.item(j).getNodeName())) {
                        TreeNode number = new TreeNode("number", 1);
                        TreeNode nu = new TreeNode(issueChildNodelist.item(j).getFirstChild().getNodeValue());
                        number.addSon(nu);
                        issue.addSon(number);
                        //issue.number = issueChildNodelist.item(j).getFirstChild().getNodeValue();  //<span style="color:#ff0000;">如图3</span>
                    } else if ("articles".equals(issueChildNodelist.item(j).getNodeName())) {
                        TreeNode articles = new TreeNode("articles", 1);
                        //List<Article> articles = new ArrayList<>();
                        //Element articlesElement =
                        NodeList articleNodeList = ((Element)issueChildNodelist.item(j)).getElementsByTagName("article");
                        //articleElement.getChildNodes();
                        for (int k = 0;k < articleNodeList.getLength(); k++) {
                            TreeNode article = new TreeNode("article",1);
                            //Article article = new Article();
                            Element articleElement = (Element)articleNodeList.item(k);
                            NodeList articleChildNodelist = articleElement.getChildNodes();
                            for (int t = 0; t < articleChildNodelist.getLength(); t++) {
                                if(articleChildNodelist.item(t).getNodeType() == Node.ELEMENT_NODE) {
                                    if("title".equals(articleChildNodelist.item(t).getNodeName())) {
                                        TreeNode title = new TreeNode("title", 1);
                                        TreeNode ti = new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue());
                                        title.addSon(ti);
                                        article.addSon(title);
                                        //article.title = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("initPage".equals(articleChildNodelist.item(t).getNodeName())) {
                                        TreeNode iP = new TreeNode("initPage", 1);
                                        TreeNode initPage = new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue());
                                        iP.addSon(initPage);
                                        article.addSon(iP);
                                        //article.initPage = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("endPage".equals(articleChildNodelist.item(t).getNodeName())) {
                                        TreeNode endPage = new TreeNode("endPage", 1);
                                        TreeNode eP = new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue());
                                        endPage.addSon(eP);
                                        article.addSon(endPage);
                                        //article.endPage = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("authors".equals(articleChildNodelist.item(t).getNodeName())) {
                                        TreeNode authors = new TreeNode("authors", 1);
                                        //List<String> authors = new ArrayList<>();
                                        NodeList authorNodeList = ((Element)articleChildNodelist.item(t)).getElementsByTagName("author");
                                        for (int p = 0; p < authorNodeList.getLength(); p++)
                                            if(authorNodeList.item(p).getNodeType() == Node.ELEMENT_NODE){
                                                TreeNode author = new TreeNode("author", 1);
                                                Node pos = ((Element)authorNodeList.item(p)).getAttributeNode("position");
                                                TreeNode posiValue = new TreeNode(pos.getNodeValue());
                                                TreeNode posi = new TreeNode("position", 1);
                                                posi.addSon(posiValue);
                                                author.addSon(posi);
                                                TreeNode authorName = new TreeNode(authorNodeList.item(p).getFirstChild().getNodeValue());
                                                author.addSon(authorName);
                                                authors.addSon(author);
                                                // authors.addSon(new TreeNode(authorNodeList.item(p).getFirstChild().getNodeValue()));
                                            }
                                        article.addSon(authors);
                                        //article.authors = authors;
                                    }
                                }
                            }
                            articles.addSon(article);
                        }
                        issue.addSon(articles);
                        //issue.articles = articles;
                    }
                }
            }
            root.addSon(issue);
        }
        //sr.issues = issues;
        return root;
    }*/

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        InputStream is = new FileInputStream("SigmodRecord.xml");
        TreeNode root = getSigModRecord(is);

        System.out.println(Tree.count(root));
        //System.out.println(root.toString());
    }
}
