package com.xie.java.distribute.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by xieyang on 6/17/17.
 */
public abstract class ZookeeperAbstractLock implements Lock{

    private static String keeperAdress ="172.16.165.150:2181";
    

    protected static String path ="/bit";
    
    protected ZkClient client = new ZkClient(keeperAdress);
    
    //阻塞式的获取锁
    public void getLock() {
        
        if(tryLock()){
            System.out.println(Thread.currentThread().getName()+"get lock");
        }else {
            waitForLock();
            tryLock();
        }
        
    }

    protected abstract void waitForLock() ;

    protected abstract boolean tryLock();

    public void unLock() {
        client.close();
    }
}
