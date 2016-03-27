package com.zhonghong.doctor.util.mode.observer;

public interface SubjectTwoParams {

    /**
     * 注册一个观察者
     */
    void addObserver(ObserverTwoParams observer);

    /**
     * 删除一个观察者
     *
     * @param observer
     * @return 是否删除成功
     */
    boolean removeObserver(ObserverTwoParams observer);

    /**
     * 通知所有的观察者
     *
     * @param id 对应观察者的id
     */
    void notifyObserver(int id, int result);
}
