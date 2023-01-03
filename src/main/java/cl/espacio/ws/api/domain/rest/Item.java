package cl.espacio.ws.api.domain.rest;

import com.fasterxml.jackson.annotation.*;

public class Item {
    private String name;
    private Detail detail;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("detail")
    public Detail getDetail() { return detail; }
    @JsonProperty("detail")
    public void setDetail(Detail value) { this.detail = value; }
}
