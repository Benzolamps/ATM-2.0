package com.feicui.atm.ui;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class DebitAtmUi extends AbstractAtmUi { // ȡ�����

	public String amount;

	@Override
	public AbstractAtmUi show() {
    	// ��ʾ����
    	CommonUtil.printLine("QT");
    	
    	// ��ȡ���, ���������е������쳣
    	String amountStr = new PropertyValidateInput("QI")
    		// �Ƿ�������
    		.addRegexCondition("amount", "UE1")
    		
    		// �Ƿ���100�ı���
    		.addRegexCondition("damount", "UE2")
    		.execute();
    	
    	// ���ַ���ת��Ϊdouble
    	double amount = Double.parseDouble(amountStr);
    	
    	// ��ӡ���
    	CommonUtil.printLine("QC", amount);
    	
    	// ȡ�����
    	AbstractAtmUi ui = new PropertyOperation<AbstractAtmUi>(this)
    		.addOperation("UL0", new MainMenuAtmUi())
    		.addOperation("UL1", this)
    		.addOperationSupply("UL2", () -> {
    			if (AbstractAtmUi.service.debit(amount)) { // ������
    				CommonUtil.printLine("QS");
    				CommonUtil.printLine("QB", amount, user.getBalance());
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
