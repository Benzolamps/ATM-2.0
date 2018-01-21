package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class BalanceAtmUi extends AbstractAtmUi { // ��ѯ������

    @Override
    public AbstractAtmUi show() {
    	// ��ʾ����
    	CommonUtil.printLine("BT");
    	
    	// ��ʾ��Ϣ
    	UserInfo info = new UserInfo(user);
        info.show();
        
        // �������˵�
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new MainMenuAtmUi())
            .action();
    }
}
