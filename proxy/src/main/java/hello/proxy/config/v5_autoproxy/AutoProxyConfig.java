package hello.proxy.config.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {
	/*
	포인트컷
	1. 프록시 적용 여부 판단 - 생성단계
	  - 자동 프록시 생성기(AnnotationAwareAspectJAutoProxyCreator)
	    는 포인컷을 사용해서 해당 빈이 프록시를 생성할 필요가 있는지 없는지 확인 한다.
	  - 모든 메소드를 전부 체크 하고 포인트컷 조건에 하나라도 맞으면 프록시 생성, 한개도 맞은게 없으면 프록시 생성X
	2. 어드바이스 적용 여부 판단 - 사용단계
	  - 프록시가 호출되었을때 부가 기능인 어드바이스를 적용할지 말지 포인트컷을 보고 판단.
	  - 메소드 중 포인트컷 조건에 만족하는 조건에 어디바이스를 호출
	
	*/
	@Bean
	public Advisor advisor1(LogTrace logTrace) {
		//포인트컷
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*","order*","save*");
		
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		
		return new DefaultPointcutAdvisor(pointcut, advice);
	}

	@Bean
	public Advisor advisor2(LogTrace logTrace) {
		//포인트컷
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		// hello.proxy.app.. 패키지와 하위 패키지의 모든 메서드는 포인트컷의 대칭 대상
		pointcut.setExpression("execution(*hello.proxy.app..*(..))");
		/*
		execution - AspectJ가 제공하는 포인트 컷 표현식
		  * : 모든 반환 타입 
		  hello.proxy.app.. : 해당 패키지와 그 하위 패키지
		  *(..) : * 모든 메소드 이름 (..) 파라미터는 상관없음
		*/
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
	
	@Bean
	public Advisor advisor3(LogTrace logTrace) {
		//포인트컷
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		// hello.proxy.app.. 패키지와 하위 패키지의 모든 메서드는 포인트컷의 대칭 대상이면서 noLog메소드는 제외
		pointcut.setExpression("execution(*hello.proxy.app..*(..)) && !execution(*hello.proxy.app..noLog(..))");
	
		LogTraceAdvice advice = new LogTraceAdvice(logTrace);
		
		return new DefaultPointcutAdvisor(pointcut, advice);
	}
}
