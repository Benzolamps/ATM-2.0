package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class TradingRecordAtmUi extends AbstractAtmUi { // ��¼ҳ��


    @Override
    public AbstractAtmUi show() {
    	// ��ʾ����
        CommonUtil.printLine("RT");
        // ��ʾ���׼�¼
        CommonUtil.printLine(user.getRecord());
        
        // �������˵�
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new MainMenuAtmUi())
            .action();
    }
}

