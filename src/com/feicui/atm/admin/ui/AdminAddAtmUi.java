package com.feicui.atm.admin.ui;

import java.util.Date;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;
import com.feicui.atm.util.ValidateInput;

public class AdminAddAtmUi extends AbstractAtmUi{

    @Override
    public AbstractAtmUi show() {
        
        AdminService service = new AdminService();
        
        // �������
        CommonUtil.printLine("AT");
        
        // ��������
        String name = ValidateInput.executeRegex("AN", ".*");
        
        // �������֤��
        String idNumber = new PropertyValidateInput("AI")
            // ��֤���֤�Ÿ�ʽ
            .addRegexCondition("idnumber", "UE8")
            
            // ����Ƿ��ظ�
            .addCondition(str -> service.getUserByIdNumber(str) == null, "UE9")
            
            .execute();
           
        // �����Ա�
        String gender = ValidateInput.executeRegex("AG", "gender");
        
        // �����ͥסַ
        String address = ValidateInput.executeRegex("AH", ".*");
        
        // ����ѧ��
        String background = ValidateInput.executeRegex("AE", "background");
        
        // ��������
        String password = ValidateInput.executeRegex("AP", "password");
        
        // �����˺�
        String accountFormat = CommonUtil.getMessage("AX");
        String account = String.format(accountFormat, gender, new Date());
        
        // ���û�������Ա�ת��Ϊ�ַ���
        gender = CommonUtil.getMessage("GEN" + gender);
        background = CommonUtil.getMessage("EDU" + background);
        
        // �����û�����
        AtmUser user = new AtmUser(account, password, name, gender, 
            background, address, idNumber);
        
        // �����ļ�
        service.addUser(user);

        // ��������ɹ�
        CommonUtil.printLine("AS", account, password);
        
        // �������˵�
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new AdminMainMenuAtmUi())
            .action();
    }   
}
