package waw.anotherworld.demon.common.http;

public class HttpResultVO {

    private String resultTimestamp;
    private String resultCode;
    private String resultMessge;
    private String resultData;


    public String getResultTimestamp() {
        return resultTimestamp;
    }

    public void setResultTimestamp(String resultTimestamp) {
        this.resultTimestamp = resultTimestamp;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessge() {
        return resultMessge;
    }

    public void setResultMessge(String resultMessge) {
        this.resultMessge = resultMessge;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
