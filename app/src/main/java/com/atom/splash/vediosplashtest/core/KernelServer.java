package com.atom.splash.vediosplashtest.core;

import com.atom.splash.vediosplashtest.task.Task;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 具体任务处理类
 * Created by atom on 2017/3/6.
 */

public class KernelServer {

    public KernelServer() {

    }
    // 创建线程池对象
    private static ExecutorService executorService;
    /**
     * 创建静态线程池个数为5个
     */
    static{
        executorService = new ScheduledThreadPoolExecutor(5);
    }

    /**
     * 创建单例模式
     */
    private static KernelServer kernelServer;
    public static KernelServer getInstance(){
        return kernelServer != null ? kernelServer : new KernelServer();
    }
    // 创建任务队列对象
    private Queue<Task> queue;

    /**
     * 执行多任务处理
     * @param queue
     */
    public void doTaskQueue(Queue<Task> queue){
        executorService.execute(new DoTask(queue));
    }

    /**
     * 执行单任务处理
     * @param task
     */
    public void doTask(Task task){
        executorService.execute(new DoTask(task));
    }

    /**
     * 线程处理
     */
    class DoTask implements Runnable{

        private Task task;
        private Queue<Task> queue;

        public DoTask(Task task) {
            this.task = task;
        }

        public DoTask(Queue<Task> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            if(null != queue){
                for(Task task : queue){
                    try {
                        Method method = task.clazz.getMethod(task.method,new Class[]{task.getClass()});
                        method.invoke(task.clazz.newInstance(), task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(null != task){
                    Method method = null;
                    try {
                        method = task.clazz.getMethod(task.method,new Class[]{task.getClass()});
                        method.invoke(task.clazz.newInstance(),task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
