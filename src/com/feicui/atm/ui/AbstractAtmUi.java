package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AtmService;

/**
 *
 * ���н���ĸ���
 *
 * @author Benzolamps
 *
 */
public abstract class AbstractAtmUi {
    protected static AtmService service = new AtmService(); // �������湲��һ̨ATM
    protected AtmUser user;

    public AbstractAtmUi() {
        user = service.getCurrentUser();
    }

    public abstract AbstractAtmUi show(); // ҵ����ʾ������, ����ֵ����һ��ҳ��
}
