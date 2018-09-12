package time_quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ע��
 * 
 * @author 30000133
 * 
 */
public class QuartzTest4 {

	public static void main(String[] args) {

		try {

			// ����scheduler��������
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// ����һ��Trigger:������
			Trigger trigger = newTrigger() //
					.withIdentity("trigger1", "group1") // ����name/group
					.startNow() // һ������scheduler��������Ч
					.withSchedule(cronSchedule("0/2 * 8-17 * * ?"))// CronTrigger// ÿ��8�c��17�c��ÿ����犈���һ�� 
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