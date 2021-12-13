package hello.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalServiceTest {

	private ThreadLocalService service = new ThreadLocalService();
	
	// 쓰레드로컬을 사용하여 각 쓰레드에 전역변수를 설정.
	@Test
	void field() {
		log.info("main start");
		Runnable userA = () -> {
			service.logic("userA");
		};
		
		Runnable userB = () -> {
			service.logic("userB");
		};
		
		Thread threadA = new Thread(userA);
		threadA.setName("thread-A");
		
		Thread threadB = new Thread(userB);
		threadB.setName("thread-B");
		
		threadA.start();
		//sleep(2000);
		sleep(100);
		threadB.start();
		sleep(3000);//메인 쓰레드 종료 대기
		log.info("main exit");
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
