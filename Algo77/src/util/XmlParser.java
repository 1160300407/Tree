package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static TreeNode getSigModRecord(InputStream inputSream) throws ParserConfigurationException, SAXException, IOException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputSream);
        Element element = document.getDocumentElement();
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
                        TreeNode vo = new TreeNode(issueChildNodelist.item(j).getFirstChild().getNodeValue());
                        issue.addSon(vo);
                        //issue.volume = issueChildNodelist.item(j).getFirstChild().getNodeValue();
                    } else if("number".equals(issueChildNodelist.item(j).getNodeName())) {
                        TreeNode nu = new TreeNode(issueChildNodelist.item(j).getFirstChild().getNodeValue());
                        issue.addSon(nu);
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
                                        article.addSon(new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue()));
                                        //article.title = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("initPage".equals(articleChildNodelist.item(t).getNodeName())) {
                                        article.addSon(new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue()));
                                        //article.initPage = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("endPage".equals(articleChildNodelist.item(t).getNodeName())) {
                                        article.addSon(new TreeNode(articleChildNodelist.item(t).getFirstChild().getNodeValue()));
                                        //article.endPage = articleChildNodelist.item(t).getFirstChild().getNodeValue();
                                    } else if ("authors".equals(articleChildNodelist.item(t).getNodeName())) {
                                        TreeNode authors = new TreeNode("authors", 1);
                                        //List<String> authors = new ArrayList<>();
                                        NodeList authorNodeList = ((Element)articleChildNodelist.item(t)).getElementsByTagName("author");
                                        for (int p = 0; p < authorNodeList.getLength(); p++)
                                            if(authorNodeList.item(p).getNodeType() == Node.ELEMENT_NODE){
                                                authors.addSon(new TreeNode(authorNodeList.item(p).getFirstChild().getNodeValue()));
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
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        InputStream is = new FileInputStream("test.xml");

        TreeNode root = getSigModRecord(is);

        System.out.println(root.toString());
    }
}
