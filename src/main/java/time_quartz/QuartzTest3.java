package time_quartz;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 注意
 * 
 * @author 30000133
 * 
 */
public class QuartzTest3 {

	public static void main(String[] args) {

		try {

			// 创建scheduler：调度器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// 定义一个Trigger:触发器
			Trigger trigger = newTrigger() //
					.withIdentity("trigger1", "group1") // 定义name/group
					.startNow() // 一旦加入scheduler，立即生效
					.withSchedule(dailyTimeIntervalSchedule()// DailyTimeIntervalTrigger
							.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(10, 36, 50))// 指定开始时间
//							.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(10, 30))// 指定结束时间
							.onDaysOfTheWeek(1, 2, 3, 4, 5)// 星期一到星期五
							.withIntervalInSeconds(5)// 5秒间隔，每5秒
//							.withRepeatCount(50)// 重复50次
							.endingDailyAfterCount(10))
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