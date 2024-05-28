package com.example.demo.java.tools.pdf;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class PdfTool {
    private static Logger logger = Logger.getLogger(PdfTool.class);
    private static final String SPLITTER = "$$";

    public static void main(String[] args) throws Exception {
        File resource = new File("E:\\Doc\\业务资料\\国自然项目申报书模板.pdf");
        PDDocument document = PDDocument.load(resource);
        // pageNum 第几页
        int pageNum = 6;
        List<PDPage> allPages = document.getDocumentCatalog().getAllPages();
        PDPage page = allPages.get(pageNum);

        CoordinatePageDrawer pageDrawer = new CoordinatePageDrawer();

        pageDrawer.processStream(page, page.findResources(), page.getContents().getStream());

        judgeCoordinate(pageDrawer.getCoordinates(), page);
    }

    private static void judgeCoordinate(List<Coordinate> coordinates, PDPage page) throws Exception {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = coordinates.size() - 1; j > i; j--) {
                if (coordinates.get(j).equals(coordinates.get(i))) {
                    coordinates.remove(j);
                }
            }
        }

        //按Y坐标升序排序
        Collections.sort(coordinates, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return (Math.abs(o1.getY() - o2.getY()) <= 2) ? 0 : o1.getY() - o2.getY();
            }
        });

        List<List<Coordinate>> eachRowCoordinates = new ArrayList<>();

        int rowIndex = 0;

        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate preCoordinate = coordinates.get(i);
            Coordinate nextCoordinate = coordinates.get(i + 1);

            if (i == 0) {
                List<Coordinate> firstRowCoordinates = new ArrayList<>();
                firstRowCoordinates.add(preCoordinate);
                eachRowCoordinates.add(firstRowCoordinates);
            }

            if (Math.abs(preCoordinate.getY() - nextCoordinate.getY()) <= 2) {
                eachRowCoordinates.get(rowIndex).add(nextCoordinate);
            } else {
                List<Coordinate> newRowCoordinates = new ArrayList<>();
                newRowCoordinates.add(nextCoordinate);
                eachRowCoordinates.add(newRowCoordinates);
                rowIndex++;
            }


        }

        //每一行都按X升序排序
        for (List<Coordinate> coordinatesOfOneRow : eachRowCoordinates) {
            Collections.sort(coordinatesOfOneRow, new Comparator<Coordinate>() {
                @Override
                public int compare(Coordinate o1, Coordinate o2) {
                    return (Math.abs(o1.getX() - o2.getX()) <= 2) ? 0 : o1.getX() - o2.getX();
                }
            });
        }

        logger.info(JSONObject.toJSONString(eachRowCoordinates));

        for (int i = 0; i < eachRowCoordinates.size() - 1; i++) {
            List<Coordinate> coordinatesOfOneRow = eachRowCoordinates.get(i);
            StringJoiner sj = new StringJoiner(SPLITTER);
            for (int j = 0; j < coordinatesOfOneRow.size() - 1; j++) {
                Coordinate preCoordinate = coordinatesOfOneRow.get(j);
                Coordinate nextCoordinate = coordinatesOfOneRow.get(j + 1);
                Coordinate newRowCoordinate = eachRowCoordinates.get(i + 1).get(0);

                String info = readRectangleInfo(preCoordinate.getX(), preCoordinate.getY(),
                        nextCoordinate.getX() - preCoordinate.getX(),
                        newRowCoordinate.getY() - preCoordinate.getY(), page);
//                if(StringUtils.isNotBlank(info)){
                sj.add(info.replaceAll("\r|\n", ""));
//                }
            }
            System.out.println(sj.toString());
        }
    }

    private static String readRectangleInfo(int x, int y, int width, int height, PDPage page) throws Exception {
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        Rectangle rect = new Rectangle(x, y, width, height);
        stripper.addRegion("rect", rect);
        stripper.extractRegions(page);
        return stripper.getTextForRegion("rect");
    }
}
