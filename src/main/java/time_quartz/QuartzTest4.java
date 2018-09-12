package time_quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 注意
 * 
 * @author 30000133
 * 
 */
public class QuartzTest4 {

	public static void main(String[] args) {

		try {

			// 创建scheduler：调度器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// 定义一个Trigger:触发器
			Trigger trigger = newTrigger() //
					.withIdentity("trigger1", "group1") // 定义name/group
					.startNow() // 一旦加入scheduler，立即生效
					.withSchedule(cronSchedule("0/2 * 8-17 * * ?"))// CronTrigger// 每天8點或17點，每兩秒鐘執行一次 
					.build();
			
			// 定义一个JobDetail：具体的任务
			JobDetail job = newJob(HelloQuartz.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
					.withIdentity("job1", "group1") // 定义name/group
					.usingJobData("name", "quartz") // 定义属性
					.build();

			// 加入这个调度
			scheduler.scheduleJob(job, trigger);

			// 启动之
			scheduler.start();

			// 运行一段时间后关闭
//			Thread.sleep(10000);
//			scheduler.shutdown(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}