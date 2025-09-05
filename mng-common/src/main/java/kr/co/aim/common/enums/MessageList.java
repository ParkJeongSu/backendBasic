package kr.co.aim.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageList {
    ALARM_REPORT("AlarmReport"),
    ARE_YOU_THERE_REPLY("AreYouThereReply"),
    ARE_YOU_THERE_REQUEST("AreYouThereRequest"),
    LOAD_COMPLETE("LoadComplete"),
    LOAD_REQUEST("LoadRequest"),
    UNLOAD_COMPLETE("UnloadComplete"),
    UNLOAD_REQUEST("UnloadRequest"),
    OP_CALL_SEND("OpCallSend"),
    COMMUNICATION_STATE_REPORT("CommunicationStateReport"),
    COMMUNICATION_STATE_CHANGED("CommunicationStateChanged"),
    MACHINE_STATE_REPORT("MachineStateReport"),
    MACHINE_STATE_CHANGED("MachineStateChanged"),
    PORT_STATE_REPORT("PortStateReport"),
    PORT_TYPE_CHANGED("PortTypeChanged"),
    PORT_USE_TYPE_CHANGED("PortUseTypeChanged"),
    PORT_STATE_CHANGED("PortStateChanged"),
    TRANSPORT_JOB_REQUEST("TransportJobRequest"),
    TRANSPORT_JOB_REPLY("TransportJobReply"),
    TRANSPORT_JOB_STARTED("TransportJobStarted"),
    TRANSPORT_JOB_COMPLETED("TransportJobCompleted"),
    CARRIER_LOCATION_CHANGED("CarrierLocationChanged"),
    TRANSPORT_JOB_CANCEL_STARTED("TransportJobCancelStarted"),
    TRANSPORT_JOB_CANCEL_COMPLETED("TransportJobCancelCompleted"),
    TRANSPORT_JOB_CANCEL_FAILED("TransportJobCancelFailed"),
    DESTINATION_CHANGED("DestinationChanged"),
    ACTIVE_TRANSPORT_JOB_REPORT("ActiveTransportJobReport"),
    INVENTORY_CARRIER_DATA_REPORT("InventoryCarrierDataReport"),
    INVENTORY_ZONE_DATA_REPORT("InventoryZoneDataReport"),
    DESTINATION_REQUEST("DestinationRequest"),
    DESTINATION_REPLY("DestinationReply"),
    CARRIER_DATA_REPORT("CarrierDataReport"),
    CARRIER_DATA_INSTALLED("CarrierDataInstalled"),
    CARRIER_DATA_REMOVED("CarrierDataRemoved"),
    OPERATION_MODE_REPORT("OperationModeReport"),
    OPERATION_MODE_CHANGED("OperationModeChanged"),
    PORT_ACCESS_MODE_CHANGED("PortAccessModeChanged"),
    CARRIER_CLEAN_JOB_STARTED("CarrierCleanJobStarted"),
    CARRIER_CLEAN_JOB_CANCELED("CarrierCleanJobCanceled"),
    CARRIER_CLEAN_JOB_ENDED("CarrierCleanJobEnded"),
    CARRIER_VALIDATION_REQUEST("CarrierValidationRequest"),
    CARRIER_VALIDATION_REPLY("CarrierValidationReply"),
    CARRIER_DEASSIGNED("CarrierDeassigned"),
    PROCESS_JOB_STARTED("ProcessJobStarted"),
    PROCESS_JOB_ABORTED("ProcessJobAborted"),
    PROCESS_JOB_DATA_REPORT("ProcessJobDataReport"),
    PROCESS_JOB_ENDED("ProcessJobEnded");


    private final String messageName;
}