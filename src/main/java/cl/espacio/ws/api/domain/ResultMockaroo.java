package cl.espacio.ws.api.domain;

public class ResultMockaroo {
    private int registerCount;

    public ResultMockaroo(int registerCount) {
        this.registerCount = registerCount;
    }

    public ResultMockaroo() {

    }

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }
}
