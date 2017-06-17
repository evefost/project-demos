package com.xie.java.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xieyang on 6/17/17.
 */
public class ZookeeperImprovetLock extends ZookeeperAbstractLock {


    private CountDownLatch downLatch;

    private String beforePath;
    private String currentPath;

    protected void waitForLock() {

        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if (downLatch != null) {
                    downLatch.countDown();
                }
            }
        };
        //监听lock节点变化
        client.subscribeDataChanges(beforePath, listener);
        if (client.exists(beforePath)) {
            downLatch = new CountDownLatch(1);
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.unsubscribeDataChanges(beforePath, listener);


    }


    /**
     * 创建临时有序节点
     *
     * @return
     */
    protected boolean tryLock() {

        if (currentPath == null || currentPath.length() == 0) {
            //currentPath = client.createEphemeralSequential(path + "/", "lock");
            currentPath = client.createEphemeralSequential(path + "/lock", "test");
        }
        List<String> children = client.getChildren(path);
        Collections.sort(children);
        if (currentPath.equals(path + "/" + children.get(0))) {
            return true;
        }
        //获取当前节点前一个节点
        int wz = Collections.binarySearch(children, currentPath.substring(path.length()+1));
        beforePath = path + '/' + children.get(wz - 1);
        return false;
    }


}
