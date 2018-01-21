package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;

public class UserInfo {
    private AtmUser user;
    public UserInfo(AtmUser user) {
        this.user = user;
    }
    
    public void show() {
        // �˺�
        CommonUtil.printLine("BA", user.getAccount());
        
        // ����
        CommonUtil.printLine("BN", user.getName());
        
        // ���֤��
        CommonUtil.printLine("BI", user.getIdNumber());
        
        // ���
        CommonUtil.printLine("BB", user.getBalance());
        
        // �Ա�
        CommonUtil.printLine("BG", user.getGender());
        
        // ��ͥסַ
        CommonUtil.printLine("BH", user.getAddress());
        
        // ѧ��
        CommonUtil.printLine("BE", user.getBackground());
    }
}
