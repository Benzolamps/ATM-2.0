package com.feicui.atm.admin.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class AdminModifyAtmUi extends AbstractAtmUi{

    @Override
    public AbstractAtmUi show() {
        
        AdminService service = new AdminService();
        
        // �������
        CommonUtil.printLine("MT");
        
        // �����˺�      
        String account = new PropertyValidateInput("MA")
                
            // �˺Ų����Ϲ���
            .addRegexCondition("number21", "UE4")
            
            // �˺Ų�����
            .addCondition(str -> service.getUserByAccount(str) != null, "UE5")
            .execute();
            
        AtmUser user = service.getUserByAccount(account);
        
        // �޸�����
        String name = modifyItem(user.getName(), "MG", ".*", null);
        user.setName(name);
        
        // �޸ļ�ͥסַ
        String address = modifyItem(user.getAddress(), "MH", ".*", null);
        user.setAddress(address);
        
        // �޸�ѧ��
        String background = 
            modifyItem(user.getBackground(), "ME", "background", null);
        user.setBackground(CommonUtil.getMessage("EDU" + background));
        
        // �޸�����
        String password = 
            modifyItem(user.getPassword(), "MP", "password", null);
        user.setPassword(password);
        
        service.modifyUser(user);
        
        // ����޸ĳɹ�
        CommonUtil.printLine("MS");
        
        // �������˵�
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new AdminMainMenuAtmUi())
            .action();
    }
    
    private String modifyItem(
        String origin, String message, String regex, String error) {
        
        // �û������ֵ, ���޸�, ͬʱ��֤�û��������ȷ��
        message = String.format(CommonUtil.getMessage(message), origin);
        String result = new PropertyValidateInput(message)
            .addCondition(str -> (
                str.isEmpty() || str.matches(CommonUtil.getRegex(regex))
            ), error).execute();
        
        return result.isEmpty() ? origin : result;   
    }
}
