package waw.anotherworld.demon.services.disclosure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisClosureResultVO {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("page_count")
    private String pageCount;

    @JsonProperty("total_count")
    private String totalCount;

    @JsonProperty("list")
    List<DisClosureVO> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<DisClosureVO> getList() {
        return list;
    }

    public void setList(List<DisClosureVO> list) {
        this.list = list;
    }
}
