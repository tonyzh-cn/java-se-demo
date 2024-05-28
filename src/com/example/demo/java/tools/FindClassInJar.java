package com.example.demo.java.tools;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FindClassInJar {
    public static void search(File rootPath, String className) throws IOException {
        if (rootPath.isFile()) {
            return;
        }
        File[] files = rootPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                search(file, className);
            } else if (file.getAbsolutePath().endsWith(".jar")) {
                ZipFile zip = new ZipFile(file.getAbsolutePath());
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().contains(className)) {
                        System.out.println(file.getAbsolutePath() + "#" + entry.getName());
                    }
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        File rootPath = new File("E:\\Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\pms-cas\\WEB-INF\\lib");
        search(rootPath, "CentralAuthenticationServiceImpl");
    }
}
