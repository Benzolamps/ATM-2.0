package com.feicui.atm.entity;

import java.io.Serializable;
import java.util.Comparator;

import com.feicui.atm.util.CommonUtil;

public class AtmUser implements Serializable, Comparable<AtmUser>, Cloneable {
    private static final long serialVersionUID = 8639640364145879480L;
    public static Comparator<AtmUser> getNameComparator() { // ����������
        return (user1, user2) -> user1.name.compareTo(user2.name);
    }
    private String account; // ����
    private String address; // ��ͥסַ
    private String background; // ѧ��
    private double balance = 0.0; // ��� 
    private String gender; // �Ա�
    private String idNumber; // ���֤��
    private String name; // ����
    private String password; // ����
    private TradingRecord tradingRecord = new TradingRecord(); // ���׼�¼
    
    public AtmUser(String idNumber) {
        this.account = "";
        this.idNumber = idNumber;
    }
    
    public AtmUser(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public AtmUser(String account,
                   String password,
                   String name,
                   String gender,
                   String background,
                   String address,
                   String idNumber) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.background = background;
        this.address = address;
        this.idNumber = idNumber;
    }

    private AtmUser() {
    }
    
    @Override
    public AtmUser clone() { // �����û�, ��ֹ�����޸��û���Ϣ
        AtmUser temp = new AtmUser();
        temp.account = account;
        temp.password = password;
        temp.name = name;
        temp.gender = gender;
        temp.background = background;
        temp.idNumber = idNumber;
        temp.address = address;
        temp.balance = balance;
        temp.tradingRecord = tradingRecord;

        return temp;
    }

    @Override
    public int compareTo(AtmUser another) { 
        // TreeSet�е�Ԫ�ر���ʵ��Comparable�ӿ�, �����ж������������ʱ����compareTo()����
        if (another.equals(this)) {
            return 0;
        }
        return account.compareTo(another.account);
    }

    // �ж������û��Ƿ�Ϊͬһ�û�
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AtmUser)) {
            return false;
        }
        
        if (account.equals(((AtmUser) obj).account)) {
            return true;
        }
        
        if (idNumber.equals(((AtmUser) obj).idNumber)) {
            return true;
        }
        
        return false;
    }


    public String getAccount() {
        return account;
    }

    public String getAddress() {
        return address;
    }

    public String getBackground() {
        return background;
    }

    public double getBalance() {
        return balance;
    }

    public String getGender() {
        return gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRecord() { // ��ý��׼�¼
        return tradingRecord.toString();
    }

    public void modify(AtmUser another) { // �޸��û�, ���ڱ����û�����
        if (another == null) {
            return;
        }
        address = another.address;
        background = another.background;
        name = another.name;
        password = another.password;
        balance = another.balance;
        tradingRecord = another.tradingRecord;
    }

    public void record(String message, Object ... args) { // ��¼����
    	message = String.format(CommonUtil.getMessage(message), args);
        tradingRecord.appendRecord(message);
    }

    /*public void setGender(String gender) {
        this.gender = gender;
    }*/

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    /*public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }*/

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.join(", ", account, idNumber, name);
    }
}
