package com.rockville.utunes_sms_handler.dto;

import com.cloudhopper.smpp.tlv.Tlv;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SmsHandler implements Serializable {
    private String destination;
    private String source;
    private byte[] messageBody;
    private int messageLength;
    private int optionalParamCount;
    private int messageId;
    private int sequenceNum;
    private ArrayList<Tlv> optionalParams;
    private String esmClass;
    private String messageEncoding;
    private int sourceTon = -1;
    private int sourceNpi = -1;
    private int destinationTon = -1;
    private int destinationNpi = -1;
    private int priority = -1;
    private int registeredDelivery = -1;
    @JsonProperty("LA")
    private String LA;
}
