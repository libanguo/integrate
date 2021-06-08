package com.example.integrate.controller;

import com.example.integrate.VO.ResponseVO;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import tool.XmlTool;
import org.dom4j.Document;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@RestController
@CrossOrigin
@RequestMapping(value = "integrate/api")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getShareLessonFromBandC")
    public ResponseVO getShareLessonFromBandC() throws IOException, SAXException, DocumentException {
        String url="http://localhost:8091/clientB/api/getShareLesson_clientB";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        ResponseVO responseVO=responseEntity.getBody();
        String xml1=(String) responseVO.getContent();
        url="http://localhost:8090/clientC/api/getShareLesson_clientC";
        responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        responseVO=responseEntity.getBody();
        String xml2=(String) responseVO.getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml1,"doc/1.xml");
        xmlTool.stringToFile(xml2,"doc/2.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ClassB.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ClassB格式不正确");
        }
        flag=xmlTool.Validatexml("doc/xsd/ClassC.xsd","doc/2.xml");
        if(!flag){
            System.out.println("ClassC格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/3.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/4.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/4.xml","doc/1.xml","doc/xsl/classToA.xsl");
        xmlTool.transformXmlByXslt("doc/3.xml","doc/2.xml","doc/xsl/classToA.xsl");
        SAXReader reader = new SAXReader();
        //2.加载xml
        String result;
        Document document = reader.read(new File("doc/1.xml"));
        Document document1=reader.read(new File("doc/2.xml"));
        //3.获取根节点
        Element rootElement1=document1.getRootElement();
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element class1 = (Element) iterator.next();
            Element class2=class1.createCopy();
            rootElement1.add(class2);
        }
        result=document1.asXML();
        return ResponseVO.buildSuccess(result);
    }

    @GetMapping("/getShareLessonFromAandC")
    public ResponseVO getShareLessonFromAandC() throws IOException, SAXException, DocumentException {
        String url="http://localhost:8090/clientC/api/getShareLesson_clientC";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        ResponseVO responseVO=responseEntity.getBody();
        String xml1=(String) responseVO.getContent();
        url="http://localhost:8092/clientA/api/getShareLesson_clientA";
        responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        responseVO=responseEntity.getBody();
        String xml2=(String) responseVO.getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml1,"doc/1.xml");
        xmlTool.stringToFile(xml2,"doc/2.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ClassC.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ClassC格式不正确");
        }
        flag=xmlTool.Validatexml("doc/xsd/ClassA.xsd","doc/2.xml");
        if(!flag){
            System.out.println("ClassA格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/3.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/4.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/3.xml","doc/1.xml","doc/xsl/classToB.xsl");
        xmlTool.transformXmlByXslt("doc/4.xml","doc/2.xml","doc/xsl/classToB.xsl");
        SAXReader reader = new SAXReader();
        //2.加载xml
        String result;
        Document document = reader.read(new File("doc/1.xml"));
        Document document1=reader.read(new File("doc/2.xml"));
        //3.获取根节点
        Element rootElement1=document1.getRootElement();
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element class1 = (Element) iterator.next();
            Element class2=class1.createCopy();
            rootElement1.add(class2);
        }
        result=document1.asXML();
        return ResponseVO.buildSuccess(result);
    }

    @GetMapping("/getShareLessonFromAandB")
    public ResponseVO getShareLessonFromAandB() throws IOException, SAXException, DocumentException {
        String url="http://localhost:8092/clientA/api/getShareLesson_clientA";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        ResponseVO responseVO=responseEntity.getBody();
        String xml1=(String) responseVO.getContent();
        url="http://localhost:8091/clientB/api/getShareLesson_clientB";
        responseEntity=restTemplate.getForEntity(url,ResponseVO.class);
        responseVO=responseEntity.getBody();
        String xml2=(String) responseVO.getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml1,"doc/1.xml");
        xmlTool.stringToFile(xml2,"doc/2.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ClassA.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ClassA格式不正确");
        }
        flag=xmlTool.Validatexml("doc/xsd/ClassB.xsd","doc/2.xml");
        if(!flag){
            System.out.println("ClassB格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/3.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/4.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/3.xml","doc/1.xml","doc/xsl/classToC.xsl");
        xmlTool.transformXmlByXslt("doc/4.xml","doc/2.xml","doc/xsl/classToC.xsl");
        SAXReader reader = new SAXReader();
        //2.加载xml
        String result;
        Document document = reader.read(new File("doc/1.xml"));
        Document document1=reader.read(new File("doc/2.xml"));
        //3.获取根节点
        Element rootElement1=document1.getRootElement();
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element class1 = (Element) iterator.next();
            Element class2=class1.createCopy();
            rootElement1.add(class2);
        }
        result=document1.asXML();
        return ResponseVO.buildSuccess(result);
    }

    @PostMapping("/dropLessonAandB")
    public ResponseVO dropLessonAandB(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        boolean flag=xmlTool.Validatexml("doc/xsd/choiceC.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceC格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/choiceToB.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/a.xml","doc/xsl/choiceToA.xsl");
        SAXReader reader=new SAXReader();
        String xmlb,xmla;
        Document document=reader.read(new File("doc/b.xml"));
        xmlb=document.asXML();
        Document document1=reader.read(new File("doc/a.xml"));
        xmla=document1.asXML();
        String url="http://localhost:8091/clientB/api/stu/dropLesson_clientB";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.postForEntity(url,xmlb,ResponseVO.class);
        String resultb=(String) responseEntity.getBody().getContent();
        if(resultb==null){
            url="http://localhost:8092/clientA/api/stu/dropLesson_clientA";
            ResponseEntity<ResponseVO> responseEntity1=restTemplate.postForEntity(url,xmla,ResponseVO.class);
            String resultc=(String) responseEntity1.getBody().getContent();
            xmlTool.stringToFile(resultc,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToC.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
        else {
            xmlTool.stringToFile(resultb,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToC.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
    }

    @PostMapping("/dropLessonAandC")
    public ResponseVO dropLessonAandC(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        boolean flag=xmlTool.Validatexml("doc/xsd/choiceB.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceB格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/a.xml","doc/xsl/choiceToA.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/c.xml","doc/xsl/choiceToC.xsl");
        SAXReader reader=new SAXReader();
        String xmlc,xmla;
        Document document=reader.read(new File("doc/a.xml"));
        xmla=document.asXML();
        Document document1=reader.read(new File("doc/c.xml"));
        xmlc=document1.asXML();
        String url="http://localhost:8090/clientC/api/stu/dropLesson_clientC";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.postForEntity(url,xmlc,ResponseVO.class);
        String resultC=(String) responseEntity.getBody().getContent();
        if(resultC==null){
            url="http://localhost:8092/clientA/api/stu/dropLesson_clientA";
            ResponseEntity<ResponseVO> responseEntity1=restTemplate.postForEntity(url,xmla,ResponseVO.class);
            String resultA=(String) responseEntity1.getBody().getContent();
            xmlTool.stringToFile(resultA,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToB.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
        else {
            xmlTool.stringToFile(resultC,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToB.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
    }

    @PostMapping("/dropLessonBandC")
    public ResponseVO dropLessonBandC(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        boolean flag=xmlTool.Validatexml("doc/xsd/choiceA.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceA格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/choiceToB.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/c.xml","doc/xsl/choiceToC.xsl");
        SAXReader reader=new SAXReader();
        String xmlc,xmlb;
        Document document=reader.read(new File("doc/b.xml"));
        xmlb=document.asXML();
        Document document1=reader.read(new File("doc/c.xml"));
        xmlc=document1.asXML();
        String url="http://localhost:8090/clientC/api/stu/dropLesson_clientC";
        ResponseEntity<ResponseVO> responseEntity=restTemplate.postForEntity(url,xmlc,ResponseVO.class);
        String resultC=(String) responseEntity.getBody().getContent();
        if(resultC==null){
            url="http://localhost:8091/clientB/api/stu/dropLesson_clientB";
            ResponseEntity<ResponseVO> responseEntity1=restTemplate.postForEntity(url,xmlb,ResponseVO.class);
            String resultB=(String) responseEntity1.getBody().getContent();
            xmlTool.stringToFile(resultB,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToA.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
        else {
            xmlTool.stringToFile(resultC,"doc/3.xml");
            xmlTool.transformXmlByXslt("doc/3.xml","doc/4.xml","doc/xsl/formatClass.xsl");
            xmlTool.transformXmlByXslt("doc/4.xml","doc/3.xml","doc/xsl/ClassToA.xsl");
            String result;
            Document document2=reader.read(new File("doc/3.xml"));
            result=document2.asXML();
            return ResponseVO.buildSuccess(result);
        }
    }

    @PostMapping("/chooseLessonAandB")
    public ResponseVO chooseLessonAandB(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ChoiceC.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceC格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/choiceToB.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/a.xml","doc/xsl/choiceToA.xsl");
        String xmlA,xmlB;
        SAXReader reader=new SAXReader();
        Document documentA=reader.read(new File("doc/a.xml"));
        Document documentB=reader.read(new File("doc/b.xml"));
        xmlA=documentA.asXML();
        xmlB=documentB.asXML();
        String url="http://localhost:8092/clientA/api/stu/chooseLesson_clientA";
        restTemplate.postForEntity(url,xmlA,ResponseVO.class);
        url="http://localhost:8091/clientB/api/stu/chooseLesson_clientB";
        restTemplate.postForEntity(url,xmlB,ResponseVO.class);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/chooseLessonAandC")
    public ResponseVO chooseLessonAandC(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ChoiceB.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceB格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/c.xml","doc/xsl/choiceToC.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/a.xml","doc/xsl/choiceToA.xsl");
        String xmlA,xmlC;
        SAXReader reader=new SAXReader();
        Document documentA=reader.read(new File("doc/a.xml"));
        Document documentC=reader.read(new File("doc/c.xml"));
        xmlA=documentA.asXML();
        xmlC=documentC.asXML();
        String url="http://localhost:8092/clientA/api/stu/chooseLesson_clientA";
        restTemplate.postForEntity(url,xmlA,ResponseVO.class);
        url="http://localhost:8090/clientC/api/stu/chooseLesson_clientC";
        restTemplate.postForEntity(url,xmlC,ResponseVO.class);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/chooseLessonBandC")
    public ResponseVO chooseLessonBandC(@RequestBody String xml) throws IOException, SAXException, DocumentException{
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xml,"doc/1.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/ChoiceA.xsd","doc/1.xml");
        if(!flag){
            System.out.println("ChoiceA格式不正确");
        }
        xmlTool.transformXmlByXslt("doc/1.xml","doc/2.xml","doc/xsl/formatClassChoice.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/choiceToB.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/c.xml","doc/xsl/choiceToC.xsl");
        String xmlC,xmlB;
        SAXReader reader=new SAXReader();
        Document documentC=reader.read(new File("doc/c.xml"));
        Document documentB=reader.read(new File("doc/b.xml"));
        xmlC=documentC.asXML();
        xmlB=documentB.asXML();
        String url="http://localhost:8090/clientC/api/stu/chooseLesson_clientC";
        restTemplate.postForEntity(url,xmlC,ResponseVO.class);
        url="http://localhost:8091/clientB/api/stu/chooseLesson_clientB";
        restTemplate.postForEntity(url,xmlB,ResponseVO.class);
        return ResponseVO.buildSuccess();
    }

    @GetMapping("/getChoosedLessonAandB/{stuId}")
    public ResponseVO getChoosedLessonAandB(@PathVariable String stuId) throws IOException, SAXException, DocumentException{
        String url="http://localhost:8091/clientB/api/stu/getChoosedLesson_clientB/"+stuId;
        String xmlB=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        url="http://localhost:8092/clientA/api/stu/getChoosedLesson_clientA/"+stuId;
        String xmlA=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xmlA,"doc/a.xml");
        xmlTool.stringToFile(xmlB,"doc/b.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/classA.xsd","doc/a.xml");
        if(!flag){
            System.out.println("ClassA格式错误");
        }
        flag=xmlTool.Validatexml("doc/xsd/classB.xsd","doc/b.xml");
        if(!flag){
            System.out.println("ClassB格式错误");
        }
        xmlTool.transformXmlByXslt("doc/a.xml","doc/1.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/b.xml","doc/2.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/1.xml","doc/a.xml","doc/xsl/classToC.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/classToC.xsl");
        SAXReader reader=new SAXReader();
        Document document=reader.read(new File("doc/a.xml"));
        Element classes=document.getRootElement();
        Document document1=reader.read(new File("doc/b.xml"));
        Element classes1=document1.getRootElement();
        Iterator iterator=classes1.elementIterator();
        while (iterator.hasNext()){
            Element element=(Element) iterator.next();
            Element class1=element.createCopy();
            classes.add(class1);
        }
        String result=document.asXML();
        return ResponseVO.buildSuccess(result);
    }

    @GetMapping("/getChoosedLessonAandC/{stuId}")
    public ResponseVO getChoosedLessonAandC(@PathVariable String stuId) throws IOException, SAXException, DocumentException{
        String url="http://localhost:8090/clientC/api/stu/getChoosedLesson_clientC/"+stuId;
        String xmlC=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        url="http://localhost:8092/clientA/api/stu/getChoosedLesson_clientA/"+stuId;
        String xmlA=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xmlA,"doc/a.xml");
        xmlTool.stringToFile(xmlC,"doc/c.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/classA.xsd","doc/a.xml");
        if(!flag){
            System.out.println("ClassA格式错误");
        }
        flag=xmlTool.Validatexml("doc/xsd/classC.xsd","doc/c.xml");
        if(!flag){
            System.out.println("ClassC格式错误");
        }
        xmlTool.transformXmlByXslt("doc/a.xml","doc/1.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/c.xml","doc/2.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/1.xml","doc/a.xml","doc/xsl/classToB.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/c.xml","doc/xsl/classToB.xsl");
        SAXReader reader=new SAXReader();
        Document document=reader.read(new File("doc/a.xml"));
        Element classes=document.getRootElement();
        Document document1=reader.read(new File("doc/c.xml"));
        Element classes1=document1.getRootElement();
        Iterator iterator=classes1.elementIterator();
        while (iterator.hasNext()){
            Element element=(Element) iterator.next();
            Element class1=element.createCopy();
            classes.add(class1);
        }
        String result=document.asXML();
        return ResponseVO.buildSuccess(result);
    }

    @GetMapping("/getChoosedLessonBandC/{stuId}")
    public ResponseVO getChoosedLessonBandC(@PathVariable String stuId) throws IOException, SAXException, DocumentException{
        String url="http://localhost:8091/clientB/api/stu/getChoosedLesson_clientB/"+stuId;
        String xmlB=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        url="http://localhost:8090/clientC/api/stu/getChoosedLesson_clientC/"+stuId;
        String xmlC=(String) restTemplate.getForEntity(url,ResponseVO.class).getBody().getContent();
        XmlTool xmlTool=new XmlTool();
        xmlTool.stringToFile(xmlC,"doc/c.xml");
        xmlTool.stringToFile(xmlB,"doc/b.xml");
        Boolean flag=xmlTool.Validatexml("doc/xsd/classC.xsd","doc/c.xml");
        if(!flag){
            System.out.println("ClassC格式错误");
        }
        flag=xmlTool.Validatexml("doc/xsd/classB.xsd","doc/b.xml");
        if(!flag){
            System.out.println("ClassB格式错误");
        }
        xmlTool.transformXmlByXslt("doc/c.xml","doc/1.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/b.xml","doc/2.xml","doc/xsl/formatClass.xsl");
        xmlTool.transformXmlByXslt("doc/1.xml","doc/c.xml","doc/xsl/classToA.xsl");
        xmlTool.transformXmlByXslt("doc/2.xml","doc/b.xml","doc/xsl/classToA.xsl");
        SAXReader reader=new SAXReader();
        Document document=reader.read(new File("doc/c.xml"));
        Element classes=document.getRootElement();
        Document document1=reader.read(new File("doc/b.xml"));
        Element classes1=document1.getRootElement();
        Iterator iterator=classes1.elementIterator();
        while (iterator.hasNext()){
            Element element=(Element) iterator.next();
            Element class1=element.createCopy();
            classes.add(class1);
        }
        String result=document.asXML();
        return ResponseVO.buildSuccess(result);
    }

}
