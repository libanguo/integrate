package tool;

import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class XmlTool {
    public boolean stringToFile(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 使用xslt转化XML文件
     * @param srcXmlPath 源XML文件路径
     * @param targetXmlPath 目标XML文件路径
     * @param xsltPath XSLT文件路径
     */
    public void transformXmlByXslt(String srcXmlPath,String targetXmlPath,String xsltPath) {

        //获取转化器工厂
        TransformerFactory tff = TransformerFactory.newInstance();

        try {
            //获取转化器对象实例s
            Transformer tf = tff.newTransformer(new StreamSource(xsltPath));
            //进行转化
            tf.transform(new StreamSource(srcXmlPath), new StreamResult(
                    new FileOutputStream(targetXmlPath)));

            System.out.println("转化成功");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            System.out.println("获取转化对象实例失败");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("没有发现源文件");
        } catch (TransformerException e) {
            e.printStackTrace();
            System.out.println("转化成目标文件失败");
        }
    }

    public boolean Validatexml(String xsdpath,String xmlpath) throws SAXException,IOException{
        //建立schema工厂
        SchemaFactory schemaFactory=SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        //建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
        File schemaFile=new File(xsdpath);
        //利用schema工厂，接收验证文档文件对象生成Schema对象
        Schema schema=schemaFactory.newSchema(schemaFile);
        //通过Schema产生针对于此Schema的验证器，利用schenaFile进行验证
        Validator validator=schema.newValidator();
        //得到验证的数据源
        Source source=new StreamSource(xmlpath);
        //开始验证，成功输出success!!!，失败输出fail
        try{

            validator.validate(source);

        }catch(Exception ex){

            ex.printStackTrace();
            return false;

        }
        return true;
    }
}
