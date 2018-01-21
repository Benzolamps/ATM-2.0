package com.feicui.atm.admin.ui;

import java.util.ArrayList;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.ui.UserInfo;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import java.util.Collections;

public class AdminInfoAtmUi extends AbstractAtmUi{

    private int index = 0; // ��ǰ�ڼ����˻�
    private ArrayList<AtmUser> users; // �����˻�
    
    public AdminInfoAtmUi() {
        AdminService service = new AdminService();
        users = service.getAllUser();
        Collections.sort(users, AtmUser.getNameComparator()); // ����������
    }
    
    private AbstractAtmUi errorForward() {
        AdminInfoAtmUi ui = new AdminInfoAtmUi();
        ui.index = index - 1;
        return ui;
    }
    
    @Override
    public AbstractAtmUi show() {
        // �������
        CommonUtil.printLine("YT");
        
        // �����index���˻�
        if (index < users.size()) {
            UserInfo info = new UserInfo(users.get(index));
            index++;
            info.show();
        } 
        
        // ���û�ʱ��ʾ
        if (index == 0) {
            CommonUtil.printLine("YE");
        }
                
        // �������˵�
        return new PropertyOperation<AbstractAtmUi>(errorForward())
            .addOperationIf("YN", this, obj -> index < users.size())
            .addOperation("UL0", new AdminMainMenuAtmUi())
            .action();
    }   
}
