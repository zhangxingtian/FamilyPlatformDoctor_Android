package com.zhonghong.doctor.util.mode;



import com.zhonghong.doctor.util.mode.observer.Observer;
import com.zhonghong.doctor.util.mode.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guobin.zheng on 2015/4/22.
 *
 *
 * 注明调用的地方。
 *
 * 加入观察着的类
 * MainActivity
 *
 * 调用刷新的地方
 * SettingFragment
 */


public class ExitAccountNotice implements Subject {
    private static Subject subject;
    private List<Observer> observers = new ArrayList<>();

    private ExitAccountNotice() {
        // TODO Auto-generated constructor stub
    }

    public static Subject getInstance() {
        if (null == subject) {
            synchronized (ExitAccountNotice.class) {
                if (null == subject)
                    subject = new ExitAccountNotice();
            }
        }
        return subject;
    }

    @Override
    public void addObserver(Observer observer) {
        // TODO Auto-generated method stub
        observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        // TODO Auto-generated method stub
        return observers.remove(observer);
    }

    @Override
    public void notifyObserver(int id) {
        // TODO Auto-generated method stub
        for (Observer observer : observers) {
            observer.update(id);
        }
    }
}
