package cl.espacio.ws.api.domain;

public class ResponseMockaroo {

    private int responseCode;
    private String description;
    private String elapsedTime;
    private ResultMockaroo result;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public ResultMockaroo getResult() {
        if (this.result == null) {
            return new ResultMockaroo();
        }
        return result;
    }

    public void setResult(ResultMockaroo result) {
        this.result = result;
    }
}
