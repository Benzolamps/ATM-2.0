package com.feicui.atm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ��һ��ѡ��, ����һ��ֵ, ȡ��switch case
 * case T return R
 * 
 * @author Benzolamps
 *
 * @param <T> ѡ����������
 * @param <R> ������������
 */
public abstract class Operation<T, R> {
    protected Map<T, Supplier<R>> map;  // �洢ѡ���뷵��ֵ
    private ArrayList<Consumer<T>> actions; // ����Ҫ������
    private R defaultR; // Ĭ�Ϸ���ֵ
    
    public Operation(R defaultR) {
        super();
        
        this.defaultR = defaultR;
        
        this.map = initialMap(); // ͨ����������Map
        if (this.map == null) {
            map = new HashMap<T, Supplier<R>>();
        }
        
        this.actions = new ArrayList<Consumer<T>>(); 
    }
 
    public Operation<T, R> addOperation(T key, R value) { // ���һ��ѡ��, �����ص�ǰ������
        // ���������StringBuffer��append()����������
        map.put(key, () -> value);
        return this;
    }
    
    public Operation<T, R> addOperationIf(T key, R value, Predicate<Object> filter) {
        // ����������ʱ, ���һ��ѡ��, �����ص�ǰ������
        if (filter.test(null)) {
            map.put(key, () -> value);
        }
        return this;
    }
    
    public Operation<T, R> addOperationSupply(T key, Supplier<R> filter) {
        // ����filter��get()������ȡֵ
        map.put(key, filter);
        return this;
    }
    
    public Operation<T, R> addAction(Consumer<T> action) { 
        // ���һ�����Ҫ������, �����ص�ǰ������
        actions.add(action);
        return this;
    }
    
    protected abstract Map<T, Supplier<R>> initialMap(); // ��ʼ��Map
    
    protected abstract T getInput(); // ��ȡ�û�������
    
    protected abstract void error(); // �������ʱ����
    
    public final R action() {
        if (map == null || map.size() == 0) { // MapΪ��
            return defaultR; // ����Ĭ��ֵ
        }
        
        T input = getInput(); // ��ȡ�û�������
        actions.forEach(action -> action.accept(input)); // ִ�ж���Ҫ������
        if (input == null) {
            error(); // ִ�д���
            return defaultR; // ����Ĭ��ֵ
        }
        
        return map.get(input).get(); // ���ض�Ӧ��ֵ
    }
}
