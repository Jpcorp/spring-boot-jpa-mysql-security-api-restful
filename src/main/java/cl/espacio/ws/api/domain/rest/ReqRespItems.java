package cl.espacio.ws.api.domain.rest;


import com.fasterxml.jackson.annotation.*;

public class ReqRespItems {
    private long responseCode;
    private String description;
    private Result result;

    @JsonProperty("responseCode")
    public long getResponseCode() { return responseCode; }
    @JsonProperty("responseCode")
    public void setResponseCode(long value) { this.responseCode = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("result")
    public Result getResult() {
        if (this.result == null) {
            return new Result();
        }
        return result; }
    @JsonProperty("result")
    public void setResult(Result value) { this.result = value; }
}
