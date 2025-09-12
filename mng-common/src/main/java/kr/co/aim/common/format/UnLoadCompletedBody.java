package kr.co.aim.common.format;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UnLoadCompletedBody {
    private String machineName;
    private String portName;
    private String carrierName;
    private String portType;
    private String portAccessMode;
}
