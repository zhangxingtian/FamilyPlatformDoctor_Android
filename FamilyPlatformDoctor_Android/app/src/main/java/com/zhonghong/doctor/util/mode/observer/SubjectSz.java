package com.zhonghong.doctor.util.mode.observer;

import java.io.Serializable;

public interface SubjectSz {

    /**
     * 注册一个观察者
     */
    void addObserver(ObserverSz observer);

    /**
     * 删除一个观察者
     *
     * @return 是否删除成功
     */
    boolean removeObserver(ObserverSz observer);

    /**
     * 通知所有的观察者
     *
     * @param id 对应观察者的id
     */
    void notifyObserver(Serializable sz, int status);

}
