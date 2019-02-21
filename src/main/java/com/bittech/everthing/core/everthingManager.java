package com.bittech.everthing.core;

import com.bittech.everthing.config.EverthingConfig;
import com.bittech.everthing.core.index.impl.FileScan;
import com.bittech.everthing.core.model.Condition;
import com.bittech.everthing.core.search.Filereach;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class everthingManager {

    private final Filereach filereach;
    private final FileScan fileScan;
    private ExecutorService executorService;

    public everthingManager(Filereach filereach, FileScan fileScan) {
        this.filereach = filereach;
        this.fileScan = fileScan;
    }


    /**
     * 检索
     * @param condition
     * @return
     */
    public List<String> search(Condition condition){
        return this.filereach.search(condition);
    }
    public void buildIndex(){
        Set<String> directores=EverthingConfig.getInstance().getIncludePath();

        if(this.executorService==null){
            this.executorService= Executors.newFixedThreadPool(directores.size(), new ThreadFactory() {
                private final AtomicInteger threadId=new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread=new Thread(r);

                    thread.setName("Thread-Scan-"+threadId.getAndIncrement());
                    return thread;
                }
            });
        }
       final CountDownLatch countDownLatch=new CountDownLatch(directores.size());

        //countDownLatch.await();
        countDownLatch.countDown();
        System.out.println("build index start...");

        for(String path:directores){
           this.executorService.submit(new Runnable() {
               @Override
               public void run() {
                   everthingManager.this.fileScan.index(path);
                   //当前任务完成，值-1
                   countDownLatch.countDown();
               }
           });
        }
        /**
         * 阻塞，直到任务完成
         */
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("build index complete...");
    }
}
