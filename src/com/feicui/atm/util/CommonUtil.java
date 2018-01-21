package com.feicui.atm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public final class CommonUtil {
    
    private static Scanner input = new Scanner(System.in); // ȫ��������
    
    public static String getRegex(String key) { // ��Properties�л�ȡ������ʽ
        if (key == null) {
            return key;
        }
        String path = "res" + File.separator + "regex.properties";
        String str = getPropertiesFromFile(path).getProperty(key);
        return str == null ? key : str;
    }
    
    public static String getMessage(String key) { // ��Properties�л�ȡ������Ϣ
        if (key == null) {
            return key;
        }
        String path = "res" + File.separator + "message.properties";
        String str = getPropertiesFromFile(path).getProperty(key);
        return str == null ? key : str; // û�ҵ�, ����ԭ�ı�
    }
    
    private static Properties getPropertiesFromFile(String path) {
        Properties prop = new Properties(); // ����Propeties����
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            prop.load(fis); // ���ļ��������м���Properties
        } catch (IOException e) {
            CommonUtil.printStackTrace(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                CommonUtil.printStackTrace(e);
            }
        }
        return prop;
    }
    
    //public static boolean isDigit(String str) { // �ж�һ���ַ����Ƿ�������
        //try {
        //	Double.valueOf(str);
        //} catch (NumberFormatException e) {
        //	return false;
        //}
        //return true;

        //String figure = CommonUtil.getRegex("digit");
        //return str.matches(figure);
    //}
    
    public static void printStackTrace(Exception e) { 
        // �Զ����쳣��Ϣ, ��ֹ���׳����쳣����
        String str = e.getClass() + ": " + e.getMessage() + "\n";
        
        for (StackTraceElement item : e.getStackTrace()) {
            str += item + "\n";
        }
        
        System.out.println(str);
    }
    
    // ��ӡ���, ���д��ڵļ�ֵ��, ��ӡ����Ӧ��ֵ, �����ӡԭֵ, ������ʱ����ӡ, ����Ϊ��ʱ����
    public static void printLine(String key, Object ... args) {
        // ��κ���д��, ������д�����
        String value = CommonUtil.getMessage(key);
        if (value != null) {
            System.out.println(String.format(value, args));
        }
    }
    
    // ���һ����ʾ��, Ȼ���ȡ�û�����; ��keyΪ���򲻴�ӡ��ʾ��
    public static String inputLine(String key, Object ... args) {
        if (!(key == null || key.isEmpty())) { // key��Ϊ��
            CommonUtil.printLine(key, args);
        }
        return CommonUtil.input.nextLine().trim(); // ȥ�����ҿո�
    }
}
