package kr.co.aim.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.aim.common.format.Header;
import kr.co.aim.common.format.Return;
import lombok.Data;

@Data
public class BaseMessage <T>{
    private Header header;
    private T body;
    //@JsonProperty("return")
    private Return reply;
}