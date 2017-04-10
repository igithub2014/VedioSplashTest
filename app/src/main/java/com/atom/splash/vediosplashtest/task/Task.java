package com.atom.splash.vediosplashtest.task;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务基类
 * Created by atom on 2017/3/6.
 */

public class Task {

    // 创建任务id
    public int taskid;
    public Class clazz;
    public String method;
    public Map<String,Object> params = new HashMap<String,Object>();

}
