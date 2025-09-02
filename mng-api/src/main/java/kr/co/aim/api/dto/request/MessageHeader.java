package kr.co.aim.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import kr.co.aim.common.format.Header;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // 모르는 필드는 무시
public class MessageHeader {
    private Header header;
}