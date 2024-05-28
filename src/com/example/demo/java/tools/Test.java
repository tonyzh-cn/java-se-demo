package com.example.demo.java.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.java.entity.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
//        PDDocument document=PDDocument.load(new File("E:\\Doc\\业务资料\\国自然项目申报书模板.pdf"));
//
//        PDFTextStripperByArea textStripperNext = new PDFTextStripperByArea();
//        Rectangle2D rectNext = new Rectangle2D.Float(496, 371, 55, 21);
//        textStripperNext.addRegion("regionNext", rectNext);
//
//        PDPage docPageNext = (PDPage) document.getDocumentCatalog().getAllPages().get(5);
//        textStripperNext.extractRegions(docPageNext);
//        System.out.println(textStripperNext.getTextForRegion("regionNext"));


//        String arr[] = "编号$$姓名$$出生年月$$性别$$职称$$学位$$单位名称$$电话$$证件号码$$项目分工$$每年工作时间（月）".split("$$".replace("$","\\$"));
//        System.out.println(arr.length);
//        JSONArray arr = new JSONArray();
//        JSONArray arr2 = new JSONArray();
//        arr.addAll(arr2);
//        System.out.println(arr);

//        User user = new User();
//        user.setId(10);
//        user.setName("zhangsan");
//        Object obj = JSON.toJSON(user);
//        System.out.println(obj);

//        String s = "2$$(一)直接费用$$70.0000$$";
//        System.out.println(s.split("\\$\\$").length);

//        String reg = "(一|二|三)+、.+";
//        Pattern pattern = Pattern.compile(reg);// 匹配的模式
//        Matcher matcher = pattern.matcher("一二、测试");
//        System.out.println(matcher.find());

//        System.out.println(new BigDecimal("1.1111111111").setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
//        BigDecimal projectFund = new BigDecimal(0);
//        System.out.println(projectFund.add(new BigDecimal("84.0000")));

//        BigDecimal bd = new BigDecimal("10.999").divide(new BigDecimal(Integer.toString(3)),6,BigDecimal.ROUND_HALF_UP);
//        double d = bd.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(String.valueOf(d));

//        String s = "252_project_Project_Source_zh";
//        System.out.println(s.substring(s.indexOf("_")+1,s.length()));
//        System.out.println(s.substring(s.lastIndexOf("_"),s.length()));

        for (int i = 0; i < 100; i++) {
            System.out.println((int) (Math.random() * 9));
        }

    }
}
