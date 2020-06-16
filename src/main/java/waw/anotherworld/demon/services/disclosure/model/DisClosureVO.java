package waw.anotherworld.demon.services.disclosure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class DisClosureVO {

    @SerializedName("corp_code")
    private String corpCode;

    @SerializedName("corp_name")
    private String corpName;

    @SerializedName("stock_code")
    private String stockCode;

    @SerializedName("corp_cls")
    private String corpCls;

    @SerializedName("report_nm")
    private String reportName;

    @SerializedName("rcept_no")
    private String reportNo;

    @SerializedName("flr_nm")
    private String flrmName;

    @SerializedName("rcept_dt")
    private String reportDate;

    @SerializedName("rm")
    private String rm;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCorpCls() {
        return corpCls;
    }

    public void setCorpCls(String corpCls) {
        this.corpCls = corpCls;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getFlrmName() {
        return flrmName;
    }

    public void setFlrmName(String flrmName) {
        this.flrmName = flrmName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }
}
