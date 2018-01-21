package com.feicui.atm.util;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

/**
 * ����ĵ�һ�����Ͳ���ΪProperties�еļ�ֵ, ��Ҫ����ת��Ϊ��Ӧ��ֵ
 * 
 * @author Benzolamps
 *
 */
public class PropertyOperation<R> extends Operation<String, R> {

    public PropertyOperation(R defaultR) {
        super(defaultR);
    }
    
    @Override
    protected Map<String, Supplier<R>> initialMap() { 
        // ʹ��LinkedHashMap��ֹ������ʾ˳�����
        return new LinkedHashMap<>();
    }
    
    @Override
    protected String getInput() {
        ArrayList<String> list = new ArrayList<>(map.keySet());
        // ��Setת��ΪArrayList���㰴����ȡֵ
        
        for (int i = 0; i < list.size(); i++) {
            String str = CommonUtil.getMessage(list.get(i));
            CommonUtil.printLine((i + 1) + ": "+ str); // ��ӡѡ��
        }
        
        String input = CommonUtil.inputLine(null); // ��ȡ�û�������
        
        if (!input.matches(CommonUtil.getRegex("option"))) { 
            return null;
        } else {            
            int number = Integer.parseInt(input);
            if (number < 1 || number > list.size()) { // ���ǹ涨������
                return null;
            }
            
            return list.get(number - 1); // ���û������ѡ��ת��Ϊѡ����������
        }       
    }

    @Override
    protected void error() {
        CommonUtil.printLine("UE0");   // ��ӡ��������  
    }
}
