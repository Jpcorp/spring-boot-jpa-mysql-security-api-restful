package cl.espacio.ws.api.domain.rest;

import com.fasterxml.jackson.annotation.*;

public class Detail {
    private String email;
    private String phoneNumber;

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }
}
