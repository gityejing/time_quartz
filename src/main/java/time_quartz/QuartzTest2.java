package time_quartz;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ע��
 * 
 * @author 30000133
 * 
 */
public class QuartzTest2 {

	public static void main(String[] args) {
		
		try {
			
			// ����scheduler��������
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// ����һ��Trigger:������
			Trigger trigger = newTrigger() //
					.withIdentity("trigger1", "group1") // ����name/group
					.startNow() // һ������scheduler��������Ч
					.withSchedule(
							calendarIntervalSchedule()// CalendarIntervalTrigger
//								.withIntervalInDays(1) // ÿ�����1��
							.withIntervalInSeconds(1)// ÿ��ִ��1��
//							.withInterval(1, IntervalUnit.SECOND)// ÿ�����10��
					).build();

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