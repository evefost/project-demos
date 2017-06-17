package com.xie.java.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.exception.ZkException;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xieyang on 6/17/17.
 */
public class ZookeeperDistribureLock extends ZookeeperAbstractLock{


    private CountDownLatch downLatch;

    protected void waitForLock()  {

      IZkDataListener listener =   new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if(downLatch !=null){
                    downLatch.countDown();
                }
            }
        };
        //监听lock节点变化
        client.subscribeDataChanges(path, listener);
        if(client.exists(path)){
            downLatch = new CountDownLatch(1);
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.unsubscribeDataChanges(path,listener);


    }


    protected boolean tryLock() {
        //尝试创建节点
        try {
            client.createEphemeral(path);
            return true;
        }catch (ZkException e){
            return false;
        }
    }




}
