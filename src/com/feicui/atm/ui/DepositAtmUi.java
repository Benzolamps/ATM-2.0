package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class DepositAtmUi extends AbstractAtmUi { // ������

	public String amount;

	@Override
	public AbstractAtmUi show() {
    	
		// ��ʾ����
    	CommonUtil.printLine("CT");
    	
    	// ��ȡ���, ���������е������쳣
    	String amountStr = new PropertyValidateInput("CI")
    		// �Ƿ�������
    		.addRegexCondition("amount", "UE1")
    		
    		// �Ƿ���100�ı���
    		.addRegexCondition("damount", "UE2")
    		.execute();
    	
    	// ���ַ���ת��Ϊdouble
    	double amount = Double.parseDouble(amountStr);
    	
    	// ��ӡ���
    	CommonUtil.printLine("CC", amount);
    	
    	// ȡ�����
    	AbstractAtmUi ui = new PropertyOperation<AbstractAtmUi>(this)
    		.addOperation("UL0", new MainMenuAtmUi())
    		.addOperation("UL1", this)
    		.addOperationSupply("UL2", () -> {
    			if (AbstractAtmUi.service.deposit(amount)) { // ������
    				CommonUtil.printLine("CS");
    				CommonUtil.printLine("CB", amount, user.getBalance());
    				// �������˵�
    				return new PropertyOperation<AbstractAtmUi>(this)
    			            .addOperation("UL0", new MainMenuAtmUi())
    			            .action();
    			} else { // ����
    				CommonUtil.printLine("UE3");
    				// ��������
    				return this;
    			}
    		}).action();
    	
    	return ui;
	}
}
