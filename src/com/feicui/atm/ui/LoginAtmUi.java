package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyValidateInput;

public class LoginAtmUi extends AbstractAtmUi { // µÇÂ¼Ò³Ãæ

    @Override
    public AbstractAtmUi show() {
        service.logout(); 
        
        // ÏÔÊ¾»¶Ó­Óï¾ä
        CommonUtil.printLine("LW0");
        
        // ÊäÈëÕËºÅ
        String account = new PropertyValidateInput("LI0")
        	// ÕËºÅ¸ñÊ½
        	.addRegexCondition("number21", "UE4")
        	
        	// ÕËºÅÊÇ·ñ´æÔÚ
        	.addCondition(str -> service.getUser(str) != null, "UE5")
        	.execute();
        
        /*String password = */new PropertyValidateInput("LI1")
        	// ÃÜÂë¸ñÊ½
        	.addRegexCondition("password", "UE10")
        	
        	// ÃÜÂëÊÇ·ñÕýÈ·
        	.addCondition(str -> 
        		service.login(new AtmUser(account, str)), "UE6")
        	.execute();
        
        return new MainMenuAtmUi();
    }
}
