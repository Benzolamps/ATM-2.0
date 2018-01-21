package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;
public class TransferAtmUi extends AbstractAtmUi { // ת�˽���
    @Override
    public AbstractAtmUi show() { 	
    	// ��ӡ����
    	CommonUtil.printLine("TT", "TI0");
    	
    	// �����˺�
        String account = new PropertyValidateInput("TI0")
        	// �˺Ÿ�ʽ
        	.addRegexCondition("number21", "UE4")
        	
        	// �˺��Ƿ����
        	.addCondition(str -> service.getUser(str) != null, "UE5")
        	
        	// �Ƿ���Լ�ת��
        	.addCondition(str -> !(user.getAccount().equals(str)), "TE")
        	.execute();
        
        // ������
        String amountString = new PropertyValidateInput("TI1")
        	// ����ʽ
        	.addRegexCondition("amount", "UE1")
        	.execute();
        
        AtmUser transferUser = service.getUser(account);
        
        double amount = Double.parseDouble(amountString);
        
        // ��ӡ�Է��˺�
        CommonUtil.printLine("TZ0", account);
        
        // ��ӡ�Է�����
        CommonUtil.printLine("TZ1", transferUser.getName());
        
        // ��ӡ���
    	CommonUtil.printLine("TZ2", amount);
    	
    	// ת�˹���
    	AbstractAtmUi ui = new PropertyOperation<AbstractAtmUi>(this)
    		.addOperation("UL0", new MainMenuAtmUi())
    		.addOperation("UL1", this)
    		.addOperationSupply("UL2", () -> {
    			if (AbstractAtmUi.service.transfer(transferUser, amount)) { 
    				// ������
    				CommonUtil.printLine("TS");
    				CommonUtil.printLine("TB", amount, user.getBalance());
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
