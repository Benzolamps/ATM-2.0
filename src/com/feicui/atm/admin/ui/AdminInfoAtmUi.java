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

    private int index = 0; // 当前第几个账户
    private ArrayList<AtmUser> users; // 所有账户
    
    public AdminInfoAtmUi() {
        AdminService service = new AdminService();
        users = service.getAllUser();
        Collections.sort(users, AtmUser.getNameComparator()); // 按姓名排序
    }
    
    private AbstractAtmUi errorForward() {
        AdminInfoAtmUi ui = new AdminInfoAtmUi();
        ui.index = index - 1;
        return ui;
    }
    
    @Override
    public AbstractAtmUi show() {
        // 输出标题
        CommonUtil.printLine("YT");
        
        // 输出第index个账户
        if (index < users.size()) {
            UserInfo info = new UserInfo(users.get(index));
            index++;
            info.show();
        } 
        
        // 无用户时显示
        if (index == 0) {
            CommonUtil.printLine("YE");
        }
                
        // 返回主菜单
        return new PropertyOperation<AbstractAtmUi>(errorForward())
            .addOperationIf("YN", this, obj -> index < users.size())
            .addOperation("UL0", new AdminMainMenuAtmUi())
            .action();
    }   
}
