package com.feicui.atm.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import com.feicui.atm.util.CommonUtil;

public class UserStorage {

    private static final String FILE_NAME = 
        "asset" + File.separator + "storage.obj"; // �ļ�·��

    public AtmUser getUserByAccount(String account) { // ͨ���˺Ų���user
        AtmUser user = new AtmUser(account, null);
        TreeSet<AtmUser> users = UserStorage.readUsers();

        if (users != null && users.contains(user)) {
            return users.floor(user).clone(); // �൱��ʹ�ü��û���ȡ���û�
        }

        return null;
    }

    public AtmUser getUserByIdNumber(String idNumber) { // ͨ�����֤�Ų���user
        AtmUser user = new AtmUser(idNumber);
        TreeSet<AtmUser> users = UserStorage.readUsers();
        if (users != null && users.contains(user)) {
            return users.floor(user).clone();
        }
        
        return null;
    }
    
    public void addUser(AtmUser user) { // ����û�
        TreeSet<AtmUser> users = UserStorage.readUsers();
        if (users != null) {
            users.add(user);
            UserStorage.writeUsers(users);
        }
    }
    
    public void removeUser(AtmUser user) { // ɾ���û�
        TreeSet<AtmUser> users = UserStorage.readUsers();
        if (users != null) {
            users.remove(user);
            UserStorage.writeUsers(users);
        }
    }

    public void modifyUser(AtmUser newUser) { // �޸��û�
        TreeSet<AtmUser> users = UserStorage.readUsers();
        AtmUser oldUser = users.floor(newUser);
        oldUser.modify(newUser);
        UserStorage.writeUsers(users);
    }
    
    public TreeSet<AtmUser> getAllUsers() {
        return readUsers();
    }

    @SuppressWarnings("unchecked")
    private static TreeSet<AtmUser> readUsers(){ // ���û�
        File file = new File(UserStorage.FILE_NAME);
        TreeSet<AtmUser> result = new TreeSet<AtmUser>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (file.isDirectory()) { // ����һ���ļ���
            throw new RuntimeException("����һ���ļ���");
        }
        if (!file.exists()) { // �ļ�δ����, ��ʼ���ļ�
            UserStorage.writeUsers(new TreeSet<AtmUser>());
        }
        else {
            try {
                fis = new FileInputStream(file);
                ois= new ObjectInputStream(fis);
                Object obj = ois.readObject();
                if (obj instanceof TreeSet<?>) {
                    result = (TreeSet<AtmUser>) obj;
                }
            } catch (ClassNotFoundException e) {
                CommonUtil.printStackTrace(e);
                file.delete();
                UserStorage.writeUsers(new TreeSet<AtmUser>()); // �ļ����ݴ���, ��ʼ���ļ�
            } catch (IOException e) {
                CommonUtil.printStackTrace(e);
                file.delete();
                UserStorage.writeUsers(new TreeSet<AtmUser>()); // �ļ����ݴ���, ��ʼ���ļ�
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    CommonUtil.printStackTrace(e);
                }
            }
        }
        return result;
    }

    private static void writeUsers(TreeSet<AtmUser> users){ // д�û�
        if (users == null) {
            return;
        }
        File file = new File(UserStorage.FILE_NAME);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
        } catch (IOException e) {
            CommonUtil.printStackTrace(e);
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                CommonUtil.printStackTrace(e);
            }
        }
    }
}
