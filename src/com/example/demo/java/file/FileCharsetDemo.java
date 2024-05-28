package com.example.demo.java.file;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

public class FileCharsetDemo {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
//        String encoding = getDecoder(new BufferedInputStream(new FileInputStream(new File("E:\\Tmp\\ansi3.txt"))));
        String encoding = getCharsetCode("E:\\Tmp\\utf8-2.txt");
        long end = System.currentTimeMillis();
        System.out.println(encoding);
        System.out.println(end - start);
    }

    private static String getCharsetCode(String fileName) throws IOException {

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int first = bin.read();
        int second = bin.read();
        int p = (first << 8) + second;

        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    public static String charset(String path) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            bis.mark(0); // 读者注： bis.mark(0);修改为 bis.mark(100);我用过这段代码，需要修改上面标出的地方。
            // Wagsn注：不过暂时使用正常，遂不改之
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                bis.close();
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) { // 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    public static String getDecoder(InputStream inputStream) {

        String encoding = null;

        try {
            byte[] buf = new byte[4096];
            UniversalDetector detector = new UniversalDetector(null);
            int nread;

            while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }

            detector.dataEnd();
            encoding = detector.getDetectedCharset();
            detector.reset();

            inputStream.close();

        } catch (Exception e) {
        }

        return encoding;
    }
}
