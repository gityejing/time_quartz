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
 * ע��
 * 
 * @author 30000133
 * 
 */
public class QuartzTest3 {

	public static void main(String[] args) {

		try {

			// ����scheduler��������
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// ����һ��Trigger:������
			Trigger trigger = newTrigger() //
					.withIdentity("trigger1", "group1") // ����name/group
					.startNow() // һ������scheduler��������Ч
					.withSchedule(dailyTimeIntervalSchedule()// DailyTimeIntervalTrigger
							.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(10, 36, 50))// ָ����ʼʱ��
//							.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(10, 30))// ָ������ʱ��
							.onDaysOfTheWeek(1, 2, 3, 4, 5)// ����һ��������
							.withIntervalInSeconds(5)// 5������ÿ5��
//							.withRepeatCount(50)// �ظ�50��
							.endingDailyAfterCount(10))
					.build();

			// ����һ��JobDetail�����������
			JobDetail job = newJob(HelloQuartz.class) // ����Job��ΪHelloQuartz�࣬����������ִ���߼�����
					.withIdentity("job1", "group1") // ����name/group
					.usingJobData("name", "quartz") // ��������
					.build();

			// �����������
			scheduler.scheduleJob(job, trigger);

			// ����֮
			scheduler.start();

			// ����һ��ʱ���ر�
//			Thread.sleep(10000);
//			scheduler.shutdown(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}