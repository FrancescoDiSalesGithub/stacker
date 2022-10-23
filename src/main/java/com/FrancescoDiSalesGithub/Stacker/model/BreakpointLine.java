package com.FrancescoDiSalesGithub.Stacker.model;

public class BreakpointLine
{
    private String objectClass;
    private String lineNumber;
    private String instruction;

    public BreakpointLine()
    {
        super();
    }

    public BreakpointLine(String objectClass,String lineNumber,String instruction)
    {
        this.objectClass = objectClass;
        this.lineNumber = lineNumber;
        this.instruction = instruction;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public String getLineNumber()
    {
        return lineNumber;
    }

    public String getInstruction()
    {
        return instruction;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
