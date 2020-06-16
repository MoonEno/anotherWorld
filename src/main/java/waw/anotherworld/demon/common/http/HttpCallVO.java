package waw.anotherworld.demon.common.http;

public class HttpCallVO {

    private String flatForm;
    private String reqURL;
    private String reqTimestamp;
    private String reqId;
    private String reqParam;

    public String getFlatForm() {
        return flatForm;
    }

    public void setFlatForm(String flatForm) {
        this.flatForm = flatForm;
    }

    public String getReqURL() {
        return reqURL;
    }

    public void setReqURL(String reqURL) {
        this.reqURL = reqURL;
    }

    public String getReqTimestamp() {
        return reqTimestamp;
    }

    public void setReqTimestamp(String reqTimestamp) {
        this.reqTimestamp = reqTimestamp;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }
}
