package com.vyo.robologger.main.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManager {

    private int theadNum;

    public ExecutorManager(int theadNum) {
        this.theadNum = theadNum;
    }

    public void initExecutor(){
        ExecutorService service = Executors.newFixedThreadPool(theadNum);
    }



}
