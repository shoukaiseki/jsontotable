package org.shoukaiseki.answerrecorder.model;

import java.util.Date;

public class Issueoption {
    private String issueoptionid;

    private String issueid;

    private String OPTION;

    private String optiondescription;

    private Date createdate;

    private String createby;

    public String getIssueoptionid() {
        return issueoptionid;
    }

    public void setIssueoptionid(String issueoptionid) {
        this.issueoptionid = issueoptionid == null ? null : issueoptionid.trim();
    }

    public String getIssueid() {
        return issueid;
    }

    public void setIssueid(String issueid) {
        this.issueid = issueid == null ? null : issueid.trim();
    }

    public String getOPTION() {
        return OPTION;
    }

    public void setOPTION(String OPTION) {
        this.OPTION = OPTION == null ? null : OPTION.trim();
    }

    public String getOptiondescription() {
        return optiondescription;
    }

    public void setOptiondescription(String optiondescription) {
        this.optiondescription = optiondescription == null ? null : optiondescription.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }
}