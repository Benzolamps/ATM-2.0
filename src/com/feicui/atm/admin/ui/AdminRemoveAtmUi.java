package com.feicui.atm.admin.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class AdminRemoveAtmUi extends AbstractAtmUi{

    @Override
    public AbstractAtmUi show() {
        // �������
        CommonUtil.printLine("DT");
        AdminService service = new AdminService();
        
        String account = new PropertyValidateInput("DA")
                
            // �˺Ų����Ϲ���
            .addRegexCondition("number21", "UE4")
            
            // �˺Ų�����
            .addCondition(str -> service.getUserByAccount(str) != null, "UE5")
            .execute();
        
        AtmUser user = service.getUserByAccount(account);
        
        /*String idNumber =*/ new PropertyValidateInput("DI")
        
            // ���֤�Ų����Ϲ���
            .addRegexCondition("idnumber", "UE8")
            
            // ���֤�����˺Ų�ƥ��
            .addCondition(str -> 
                service.getUserByIdNumber(str).equals(user), "UE7")
            .execute();
        
        // �Ƴ��û�
        service.removeUser(user);
        
        // ��ӡ�����ɹ�
        CommonUtil.printLine("DS");
        
        // �ص����˵�
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new AdminMainMenuAtmUi())
            .action();     
    }   
}
