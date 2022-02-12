package com.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 相同的应用程序运行于多个服务器集群上，是为了解决单台服务面对高并发处理不来的情况。
 * 有一台机器挂掉了，其他机器如何感知到这一变化并接管任务？
 * 用户量突然的爆增，需要增加机器来缓解压力，如何做到不重启集群而完成机器的添加？
 * 分布式系统，怎么高效协同多台服务对同一网络文件进行写操作
 * zookeeper就是一种用于分布式应用程序的高性能协调服务
 * <p>
 * 比如我们现在拥有这么一个集群，集群里面有个缓存服务，集群中每个程序都会用到这个缓存，
 * 如果此时缓存中有一项缓存过期了，在大并发环境下，同一时刻中许许多多的服务都过来访问缓存，
 * 获取缓存中的数据，发现缓存过期，就要再去数据库取，然后更新到缓存服务中去。
 * 但是其实我们仅仅只需要一个请求过来数据库去更新缓存即可，然后这个场景，我们该怎么去做？
 * 这是就可以使用分布式锁
 *
 * @author KPQ
 * @date 2021/11/2
 */
public class CuratorLockTest {

    private CuratorFramework client = null;
    private static final String zkServerPath = "127.0.0.1:2181";
    private static final String nodePath = "/hadoop/yarn";

    @Before
    public void init() {
        //zk客户端与zk服务端的会话超时时间
        final int sessionTimeOut = 60 * 1000;
        final int connectionTimeOut = 30 * 1000;
        //重试策略:重试之间等待的初始时间,最大重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(sessionTimeOut)
                .connectionTimeoutMs(connectionTimeOut)
                .retryPolicy(retryPolicy).build();
        client.start();
    }

    @Test
    public void getStatus() {
        CuratorFrameworkState state = client.getState();
        System.out.println("服务是否已经启动:" + (state == CuratorFrameworkState.STARTED));
    }

    /**
     * 创建节点
     *
     * @throws Exception
     */
    @Test
    public void createNodes() throws Exception {
        byte[] data = "abc".getBytes();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)      //节点类型
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(nodePath, data);
    }

    @Test
    public void getNode() throws Exception {
        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath(nodePath);
        System.out.println("节点数据:" + new String(data));
        System.out.println("节点信息:" + stat.toString());
    }

    /**
     * 不可重入共享锁
     */
    @Test
    public void interProcessMutex() {
        String path = "/test";
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, path);
    }



    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }


}
