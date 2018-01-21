package com.feicui.atm.admin.ui;

import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class AdminMainMenuAtmUi extends AbstractAtmUi{

    @Override
    public AbstractAtmUi show() {
        // ��ӡ���˵�����
        CommonUtil.printLine("MM");
        
        // ȥ����������      
        AbstractAtmUi dest = new PropertyOperation<AbstractAtmUi>(this)
            // ����
            .addOperation("MMA0", new AdminAddAtmUi())
    
            // ����
            .addOperation("MMA1", new AdminRemoveAtmUi())
    
            // �鿴�����˻���Ϣ
            .addOperation("MMA2", new AdminInfoAtmUi())
    
            // �޸��˻���Ϣ
            .addOperation("MMA3", new AdminModifyAtmUi())
    
            // �˳�
            .addOperation("MMA4", new AdminLoginAtmUi())
            
            .action();
        
        return dest;
    }
}
