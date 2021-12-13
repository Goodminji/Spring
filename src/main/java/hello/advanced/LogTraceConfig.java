package hello.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

	@Bean
	public LogTrace logTrace() {
		//return new FieldLocalLogTrace();
		return new ThreadLocalLogTrace();//쓰레드 로컬로 변경
		//쓰레드 로컬은 커넥션 풀에서 사용하기 때문에 무조건 제거하는 부분이 필수
	}
}
