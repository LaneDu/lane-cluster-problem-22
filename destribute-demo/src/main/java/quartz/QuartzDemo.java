package quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author lane
 * @date 2021年05月14日 上午10:51
 */
public class QuartzDemo {

    //1. 创建定时任务调度器（类似于公交调度站）
    public static Scheduler createScheduler() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        return scheduler;

    }
    //2. 创建任务 类似与公交运输

    public static JobDetail createJob(){

        JobBuilder jobBuilder = JobBuilder.newJob(JobDemo.class);
        jobBuilder.withIdentity("jobName","myJob");
        JobDetail jobDetail = jobBuilder.build();

        return jobDetail;

    }


    /**
     * 3、创建作业任务时间触发器（类似于公交车出车时间表）
     * cron表达式由七个位置组成，空格分隔
     * 1、Seconds（秒）  0~59
     * 2、Minutes（分）  0~59
     * 3、Hours（小时）  0~23
     * 4、Day of Month（天）1~31,注意有的月份不足31天
     * 5、Month（月） 0~11,或者 JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV,DEC
     * 6、Day of Week(周)  1~7,1=SUN或者  SUN,MON,TUE,WEB,THU,FRI,SAT
     * 7、Year（年）1970~2099  可选项
     *示例：
     * 0 0 11 * * ? 每天的11点触发执行一次
     * 0 30 10 1 * ? 每月1号上午10点半触发执行一次
     */
    public static Trigger createTrigger(){
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "myTrigger")
                .startNow() //2s触发一次
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();

        return trigger;
    }

    //4. 主程序执行
    public static void main(String[] args) {

        try {
            //1. 创建任务调度主程序
            Scheduler scheduler = QuartzDemo.createScheduler();
            //2. 创建任务
            JobDetail jobDetail = QuartzDemo.createJob();
            //3. 创建任务触发器
            Trigger trigger = QuartzDemo.createTrigger();
            //4. 执行调度任务
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }



}
