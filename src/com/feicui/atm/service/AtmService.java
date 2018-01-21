package com.feicui.atm.service;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.entity.UserStorage;

public class AtmService {
    private AtmUser currentUser;

    public AtmUser getCurrentUser() {
        return currentUser;
    }

    public boolean login(AtmUser user) { // ��֤��¼����
        UserStorage storage = new UserStorage();
        AtmUser temp = storage.getUserByAccount(user.getAccount());
        if (temp == null) {
            return false;
        }
        //System.out.println(temp.getPassword());
        if (!user.getPassword().equals(temp.getPassword())) {
            return false;
        }
        currentUser = temp;
        return true;
    }

    public boolean logout() {
        currentUser = null;
        return true;
    }

    // ͨ��һ���ٵ��û����������û�������
    public AtmUser getUser(String str) {
        UserStorage storage = new UserStorage();
        AtmUser user = storage.getUserByAccount(str);
        if (user == null) {
            user = storage.getUserByIdNumber(str);
        }
        return user;
    }

    public boolean transfer(AtmUser transferUser, double amount) { // ת��
        UserStorage storage = new UserStorage();
        if (amount > currentUser.getBalance()) { // ����
            return false;
        } else {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.record("TR0", transferUser.getName(), amount);
            transferUser.setBalance(transferUser.getBalance() + amount);
            transferUser.record("TR1", currentUser.getName(), amount);
            storage.modifyUser(currentUser);
            storage.modifyUser(transferUser);
            return true;
        }
    }

    public boolean deposit(double amount) { // ���
        UserStorage storage = new UserStorage();
        currentUser.setBalance(currentUser.getBalance() + amount);
        currentUser.record("CR", amount);
        storage.modifyUser(currentUser);
        return true;
    }

    public boolean debit(double amount) { // ȡ��
        if (amount > currentUser.getBalance()) { // ����
            return false;
        } else {
            UserStorage storage = new UserStorage();
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.record("QR", amount);
            storage.modifyUser(currentUser);
            return true;
        }
    }
}
