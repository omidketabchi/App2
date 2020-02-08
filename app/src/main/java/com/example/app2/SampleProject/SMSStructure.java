package com.example.app2.SampleProject;

public class SMSStructure {

    private String Messages[];
    private String MobileNumbers[];
    private String LineNumber;
    private String SendDateTime;
    private String CanContinueInCaseOfError;

    public SMSStructure(String[] Messages,
                        String[] MobileNumbers,
                        String LineNumber,
                        String SendDateTime,
                        String CanContinueInCaseOfError) {

        this.Messages = Messages;
        this.MobileNumbers = MobileNumbers;
        this.LineNumber = LineNumber;
        this.SendDateTime = SendDateTime;
        this.CanContinueInCaseOfError = CanContinueInCaseOfError;
    }

}
