package kr.co.aim.common.record;

import java.util.Date;

public record TransactionInfo(
        String eventName,
        String timeKey,
        Date eventTime,
        String eventUser,
        String eventComment
) {
    public static TransactionInfo now(String eventName, String user, String comment, String timeKey) {
        return new TransactionInfo(eventName, timeKey, new Date(), user, comment);
    }
}