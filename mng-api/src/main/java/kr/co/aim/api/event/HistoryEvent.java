package kr.co.aim.api.event;

// "History 기록이 필요한 이벤트"라는 것을 나타내는 마커 인터페이스
public interface HistoryEvent {
    String getEventType(); // "ALARM", "USER" 등
    Object getSourceEntity(); // 변경된 도메인 객체 (Alarm, User 등)
}