package com.feicui.atm.service;

import java.util.ArrayList;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.entity.UserStorage;
import com.feicui.atm.util.CommonUtil;

public class AdminService extends AtmService {
    
    public boolean login(String account, String password) {
        String account1 = CommonUtil.getMessage("AA");
        String password1 = CommonUtil.getMessage("AP");
        if (account.equals(account1) && password.equals(password1)) {
            return true;
        }
        
        return false;
    }
    
    public void addUser(AtmUser user) {
        UserStorage storage = new UserStorage();
        storage.addUser(user);
    }
    
    public void removeUser(AtmUser user) {
        UserStorage storage = new UserStorage();
        storage.removeUser(user);
    }
    
    public void modifyUser(AtmUser user) {
        UserStorage storage = new UserStorage();
        storage.modifyUser(user);
    }
    
    public ArrayList<AtmUser> getAllUser() {
        UserStorage storage = new UserStorage();
        return new ArrayList<AtmUser>(storage.getAllUsers());
    }
    
    public AtmUser getUserByIdNumber(String idNumber) {
        UserStorage storage = new UserStorage();
        return storage.getUserByIdNumber(idNumber);
    }
    
    public AtmUser getUserByAccount(String account) {
        UserStorage storage = new UserStorage();
        return storage.getUserByAccount(account);
    }
}
