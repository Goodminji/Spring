package hello.advanced.trace;

public class TraceStatus {
 // 로그 시작할때의 상태 정보를 가지고 있고 이 상태 정보는 로그를 종료할때 사용 된다.
	private TraceId traceId;
	private Long startTimeMs;
	private String message;
	
	public TraceStatus(TraceId traceId,Long startTimeMs, String message) {
		this.traceId = traceId;
		this.startTimeMs = startTimeMs;
		this.message = message;
	}

	public TraceId getTraceId() {
		return traceId;
	}

	public Long getStartTimeMs() {
		return startTimeMs;
	}

	public String getMessage() {
		return message;
	}
}
