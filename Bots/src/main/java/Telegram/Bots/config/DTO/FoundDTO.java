package Telegram.Bots.config.DTO;


import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class FoundDTO {
    private long id;
    private String name;
    private int tier;
    private String description;
    private String shortDescription;
    private int gainers;
    private int losers;
    private double dominance;
    private double marketCap;
    private double volume24h;

    @Override
    public String toString(){
        if(!this.getDescription().equals("Нет"))
            return  "Имя фонда - "+this.getName()+"\n"+
                    "Тир фонда - "+this.getTier()+"\n"+
                    "Описание - "+this.getDescription()+"\n"+
                    "Удавшиеся вложения - "+this.getGainers()+"\n"+
                    "Неудавшиеся вложения - "+this.getLosers()+"\n"+
                    "Доминация на рынке в % - "+this.getDominance()+"\n"+
                    "Капитализация фонда - "+this.getMarketCap()+"$\n"+
                    "Объем торгов за 24ч - "+this.getVolume24h()+"$";
        else if (!this.getShortDescription().equals("Нет"))
            return  "Имя фонда - "+this.getName()+"\n"+
                    "Тир фонда - "+this.getTier()+"\n"+
                    "Описание - "+this.getShortDescription()+"\n"+
                    "Удавшиеся вложения - "+this.getGainers()+"\n"+
                    "Неудавшиеся вложения - "+this.getLosers()+"\n"+
                    "Доминация на рынке в % - "+this.getDominance()+"\n"+
                    "Капитализация фонда - "+this.getMarketCap()+"$\n"+
                    "Объем торгов за 24ч - "+this.getVolume24h()+"$";
        else return "Имя фонда - "+this.getName()+"\n"+
                    "Тир фонда - "+this.getTier()+"\n"+
                    "Удавшиеся вложения - "+this.getGainers()+"\n"+
                    "Неудавшиеся вложения - "+this.getLosers()+"\n"+
                    "Доминация на рынке в % - "+this.getDominance()+"\n"+
                    "Капитализация фонда - "+this.getMarketCap()+"$\n"+
                    "Объем торгов за 24ч - "+this.getVolume24h()+"$";
    }

    public FoundDTO(JsonObject obj){
       this.id = obj.getAsJsonPrimitive("id").getAsLong();
       this.name = obj.getAsJsonPrimitive("name").getAsString();

       if (obj.get("tier").isJsonNull() == false)
       this.tier = obj.getAsJsonPrimitive("tier").getAsInt();
       else this.tier = 0;

       if (obj.get("description").isJsonNull() == false)
       this.description = obj.getAsJsonPrimitive("description").getAsString();
       else this.description = "Нет";

       if (obj.get("shortDescription").isJsonNull() == false )
       this.shortDescription = obj.getAsJsonPrimitive("shortDescription").getAsString();
       else this.shortDescription = "Нет";

       this.gainers = obj.getAsJsonPrimitive("gainers").getAsInt();
       this.losers = obj.getAsJsonPrimitive("losers").getAsInt();
       this.dominance = obj.getAsJsonPrimitive("dominance").getAsDouble();
       this.marketCap = obj.getAsJsonPrimitive("marketCap").getAsDouble();
       this.volume24h = obj.getAsJsonPrimitive("volume24h").getAsDouble();
    }
}
