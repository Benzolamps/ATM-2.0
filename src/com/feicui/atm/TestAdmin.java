package com.feicui.atm;

import com.feicui.atm.admin.ui.AdminLoginAtmUi;
import com.feicui.atm.ui.AbstractAtmUi;

public class TestAdmin {
    public static void main(String[] args) {
        AbstractAtmUi ui = new AdminLoginAtmUi(); // ����տ�ʼ���е�¼����

        /*
         * ��ʾ��ǰ���棬����uiָ����һ������
         */
        while (ui != null) {
            ui = ui.show();
        }
        
    }
}
