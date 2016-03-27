package com.zhonghong.doctor.util.mode.observer;

import java.io.Serializable;

public interface ObserverSz {
	void update(Serializable sz, int status);
}
