package com.tuan800.lbs.trans;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author caomiao
 */
public class TransferName {

    private Map<String, String> nameTable = new HashMap<String, String>();

    private List<String> getFileList(String dirPath) {
        File dir = new File(dirPath);
        File[] fileList = dir.listFiles();
        List<String> fileNameList = new ArrayList<String>();
        for (File file : fileList) {
            fileNameList.add(file.getName());
        }
        return fileNameList;
    }

    private void readNameTable(String filePath, String charSet) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] names = line.split("\t");
                nameTable.put(names[0], names[1]);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void transferData(String dirPath, String transDirPath, String charSet) {
        List<String> fileList = getFileList(dirPath);
        int counter = 1;
        for (String file : fileList) {
            try {
                String head = file.replace(".txt", "") + "\t";
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dirPath + file), charSet));
                String transName = counter + ".txt";
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(transDirPath + transName), charSet));
                String line = null;
                while ((line = br.readLine()) != null) {
                    line = head + line + "\n";
                    bw.append(line);
                }
                counter++;
                bw.flush();
                bw.close();
                br.close();
                System.out.println(transDirPath + transName);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void run() {
        transferData("E:/tuan800/bzone&cate/bizone/", "C:/Users/caomiao/Desktop/trans/", "UTF-8");
    }

    public static void main(String[] args) {
        TransferName trans = new TransferName();
        trans.run();
    }
}
