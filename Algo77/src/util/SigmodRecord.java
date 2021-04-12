package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * 根据XML文件解析得来最外层User标签
 */
class SigmodRecord {
    private List<Issue> issues;
}

class Issue {
    private int volume;
    private int number;
    private List<Article> articles;
}

class Article {
    private String title;
    private int initPage;
    private int endPage;
    private List<Author> authors;
}

class Author {
    List<String> author;
}

public class DomParseService {

    public static SigmodRecord getBooks(InputStream inputSream) throws ParserConfigurationException, SAXException, IOException, IOException, SAXException {
        //List<Book> list = new ArrayList<Book>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputSream);
        Element element = document.getDocumentElement();
        NodeList issueNodelist = element.getElementsByTagName("issue");//所有的book 节点
        SigmodRecord sr = new SigmodRecord();
        for(int i=0;i<issueNodelist.getLength();i++) {
            Issue issue = new Issue();
           // Book book = new Book();
            Element issueElement = (Element)issueNodelist.item(i);//这个item() 函数返回的类型DeferredElementImpl，是Element 接口的实现类，Element 实现了Node，<span style="color:#ff0000;">如图1</span>，这个函数本身返回的是Node 接口类型
            book.setId(Integer.parseInt(bookElement.getAttribute("id")));
            NodeList bookChildNodelist = bookElement.getChildNodes();
            for(int j = 0;j < bookChildNodelist.getLength();j++) {
                if(bookChildNodelist.item(j).getNodeType() == Node.ELEMENT_NODE) { //看看这里面有什么属性node（有TEXT_NODE ，即那些空白，<span style="color:#ff0000;">如图2</span>）
                    if("name".equals(bookChildNodelist.item(j).getNodeName())) {
                        book.setName(bookChildNodelist.item(j).getFirstChild().getNodeValue());
                    } else if("price".equals(bookChildNodelist.item(j).getNodeName())) {
                        book.setPrice(Float.parseFloat(bookChildNodelist.item(j).getFirstChild().getNodeValue()));  //<span style="color:#ff0000;">如图3</span>
                    }
                }
            }
            list.add(book);
        }
        return list;
    }
}