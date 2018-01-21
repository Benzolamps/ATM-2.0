package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class MainMenuAtmUi extends AbstractAtmUi { // ������

	@Override
	public AbstractAtmUi show() {

        // ��ӡ���˵�����
        CommonUtil.printLine("MM");
        
        // ȥ����������      
        AbstractAtmUi dest = new PropertyOperation<AbstractAtmUi>(this)
            // ��ѯ
            .addOperation("MM1", new BalanceAtmUi())
    
            // ת��
            .addOperation("MM2", new DebitAtmUi())
    
            // ȡ��
            .addOperation("MM3", new DepositAtmUi())
    
            // ���
            .addOperation("MM4", new TransferAtmUi())
    
            // ��ѯ���׼�¼
            .addOperation("MM5", new TradingRecordAtmUi())
            
            // �˿�
            .addOperation("MM6", new LoginAtmUi())
            
            .action();
        
        return dest;
	}
}
