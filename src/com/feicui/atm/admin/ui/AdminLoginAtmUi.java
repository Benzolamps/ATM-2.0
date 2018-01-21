package com.feicui.atm.admin.ui;

import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.ValidateInput;

public class AdminLoginAtmUi extends AbstractAtmUi{

    @Override
    public AbstractAtmUi show() {
        // ��ʾ��ӭ���
        CommonUtil.printLine("LW1");
        
        // �������Ա�û���
        ValidateInput.executeInstance("LI0", str -> str.equals("1234"), "UE5");
        
        // �������Ա����
        ValidateInput.executeInstance("LI1", str -> str.equals("5678"), "UE6");
        
        // �������˵�
        return new AdminMainMenuAtmUi();
    }  
}
