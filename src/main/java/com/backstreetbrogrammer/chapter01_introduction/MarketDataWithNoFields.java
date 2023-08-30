package com.backstreetbrogrammer.chapter01_introduction;

import java.io.Serializable;

//when we are going to serialize it via ObjectOutputStream.writeObject()
//        then it will throw NotSerializableException
public class MarketDataWithNoFields implements Serializable {
    private static final long serialVersionUID = 311896084136253790L;
}
