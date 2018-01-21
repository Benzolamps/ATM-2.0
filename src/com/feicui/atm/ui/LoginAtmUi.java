package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyValidateInput;

public class LoginAtmUi extends AbstractAtmUi { // ��¼ҳ��

    @Override
    public AbstractAtmUi show() {
        service.logout(); 
        
        // ��ʾ��ӭ���
        CommonUtil.printLine("LW0");
        
        // �����˺�
        String account = new PropertyValidateInput("LI0")
        	// �˺Ÿ�ʽ
        	.addRegexCondition("number21", "UE4")
        	
        	// �˺��Ƿ����
        	.addCondition(str -> service.getUser(str) != null, "UE5")
        	.execute();
        
        /*String password = */new PropertyValidateInput("LI1")
        	// �����ʽ
        	.addRegexCondition("password", "UE10")
        	
        	// �����Ƿ���ȷ
        	.addCondition(str -> 
        		service.login(new AtmUser(account, str)), "UE6")
        	.execute();
        
        return new MainMenuAtmUi();
    }
}
