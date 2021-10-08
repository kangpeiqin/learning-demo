package com.example.client.rest;

import cn.hutool.json.JSONUtil;
import com.example.client.delayQueue.model.DelayJob;
import com.example.client.delayQueue.model.Job;
import com.example.client.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KPQ
 * @date 2021/10/8
 */
@RestController
@RequestMapping("delay")
public class DelayController {

    @Autowired
    private JobService jobService;

    /**
     * 添加
     *
     * @param request
     * @return
     */
    @PostMapping(value = "add")
    public String addDefJob(@RequestBody Job request) {
        DelayJob delayJob = jobService.addDefJob(request);
        return JSONUtil.toJsonStr(delayJob);
    }

    /**
     * 获取
     *
     * @return
     */
    @GetMapping(value = "pop")
    public String getProcessJob(String topic) {
        Job process = jobService.getProcessJob(topic);
        return JSONUtil.toJsonStr(process);
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    @RequestMapping(value = "finish", method = RequestMethod.DELETE)
    public String finishJob(String jobId) {
        jobService.finishJob(jobId);
        return "success";
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String deleteJob(String jobId) {
        jobService.deleteJob(jobId);
        return "success";
    }

}