package com.baron.model;

import com.baron.pool.WebDriverPool;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Spider;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by Baron.Chen on 2017/6/9.
 */
public class SpiderTask {
    private SpiderTemplate spiderTemplate;
    private Long successCount;
    private Long failedCount;
    private Date startedDate;
    private Date finishedDate;
    private BlockingQueue<TaskUnit> taskUnitResults;
    private List<Integer> threadIds;
    private ExecutorService threadPool;
    private WebDriverPool webDriverPool;
    private Spider spider;
    private State state;

    public SpiderTask() {
    }

    public List<Integer> getThreadIds() {
        return threadIds;
    }

    public void setThreadIds(List<Integer> threadIds) {
        this.threadIds = threadIds;
    }

    public SpiderTemplate getSpiderTemplate() {
        return spiderTemplate;
    }

    public void setSpiderTemplate(SpiderTemplate spiderTemplate) {
        this.spiderTemplate = spiderTemplate;
    }

    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    public Long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Long failedCount) {
        this.failedCount = failedCount;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public BlockingQueue<TaskUnit> getTaskUnitResults() {
        return taskUnitResults;
    }

    public void setTaskUnitResults(BlockingQueue<TaskUnit> taskUnitResults) {
        this.taskUnitResults = taskUnitResults;
    }

    public Spider getSpider() {
        return spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
