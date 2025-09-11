package kr.co.aim.api.event.impl;

import kr.co.aim.api.event.HistoryEvent;
import kr.co.aim.domain.model.Alarm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlarmHistoryEvent implements HistoryEvent {
    private final Alarm alarm;

    @Override
    public String getEventType() {
        return "ALARM";
    }

    @Override
    public Object getSourceEntity() {
        return alarm;
    }
}