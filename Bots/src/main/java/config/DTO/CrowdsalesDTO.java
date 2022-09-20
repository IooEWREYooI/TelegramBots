package config.DTO;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class CrowdsalesDTO {
    private long id;
    private String type;
    private String lockupPeriod;
    private long tokensForSale;
    private String start;
    private String end;
    private String status;
    private double priceInUSD;
    private long raiseInUSD;

    public CrowdsalesDTO(JsonObject obj){
        if (obj.has("id") && !obj.get("id").isJsonNull()){
            this.id = obj.get("id").getAsLong();
        } else this.id = 0;

        if (obj.has("type") && !obj.get("type").isJsonNull()){
            this.type = obj.get("type").getAsString();
        } else this.type = "Неизвестно";

        if (obj.has("lockupPeriod") && !obj.get("lockupPeriod").isJsonNull()){
            this.lockupPeriod = obj.get("lockupPeriod").getAsString();
        } else this.lockupPeriod = "Неизвестно";

        if (obj.has("tokensForSale") && !obj.get("tokensForSale").isJsonNull()){
            this.tokensForSale = obj.get("tokensForSale").getAsLong();
        } else this.tokensForSale = 0;

        if (obj.has("start") && !obj.get("start").isJsonNull()){
            this.start = obj.get("start").getAsString();
        } else this.start = "Неизвестно";

        if (obj.has("end") && !obj.get("end").isJsonNull()){
            this.end = obj.get("end").getAsString();
        } else this.end = "Неизвестно";

        if (obj.has("status") && !obj.get("status").isJsonNull()){
            this.status = obj.get("status").getAsString();
        } else this.status = "Неизвестно";

        if (obj.has("price") && !obj.get("price").isJsonNull() && !obj.get("price").getAsJsonObject().isJsonNull() && obj.get("price").getAsJsonObject().has("USD")){
            this.priceInUSD = obj.get("price").getAsJsonObject().get("USD").getAsLong();
        } else this.priceInUSD = 0;

        if (obj.has("raise") && !obj.get("raise").isJsonNull() && !obj.get("raise").getAsJsonObject().isJsonNull() && obj.get("raise").getAsJsonObject().has("USD")){
            this.raiseInUSD = obj.get("raise").getAsJsonObject().get("USD").getAsLong();
        } else this.raiseInUSD = 0;
    }
}
