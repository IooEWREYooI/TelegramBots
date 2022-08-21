package config;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;

import io.github.furstenheim.CopyDown;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class API_CoinGeko {
	final DecimalFormat df = new DecimalFormat("0.0"); 
	
	public String API_CoinGeko_coin (String coin) throws IOException, ConnectException{
		String text =  "*Криптовалюта не найдена*, _попробуйте ввести ее символ или смиритесь :(_";
		ArrayList<String> categoriesArray = new ArrayList<String>();
		String ecosistems = "";
		String ethereumAdress = "";
	    String binance_smart_chain = "";
	    String link = "";
	    String USD_market_cap = "\n";
	    String SOL = "";
		String Matic = "";
		String TRON = "";
		String ZIL = "";
		String TERRA = "";
		String ALGO = "";
		String NEAR = "";
		String DOT = "";
		String ARB_ONE = "";
		String FANTOM = "";
		String OSMO = "";
		String AVAX = "";
		String OPT_ETH = "";
		String BOBA = "";
		String CELO = "";
		String BEAM = "";
		String OASIS = "";
		
		URL url = new URL("https://api.coingecko.com/api/v3/coins/"+coin+"");
		URLConnection connection =  url.openConnection();
				
		JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream()));
		JsonElement jsonElement = JsonParser.parseReader(json);
		if (jsonElement.isJsonNull() == false) {
		// Создание главного объекта
	    JsonObject rootObject = jsonElement.getAsJsonObject();
	    
	    if(rootObject.has("categories") && rootObject.getAsJsonArray("categories").isJsonNull() == false) {
	    JsonArray categoriesObjectArray = rootObject.getAsJsonArray("categories");
	    
	    int i = 0;
		    for(var categoriesElement:categoriesObjectArray) {
		    	categoriesArray.add(i, categoriesElement.getAsString());
		    	i++;
		    }
		    StringBuilder ecosistem = new StringBuilder(categoriesArray.toString());
		    ecosistem.deleteCharAt(0);
		    ecosistem.deleteCharAt(ecosistem.length()-1);
		    if (ecosistem.isEmpty() == false && ecosistem.toString().equals("") && false || ecosistem.toString().equals(" "))
		    	ecosistems = "\nЭкосистемы: *"+ecosistem+"*\n";
		    else 
		    	ecosistems = "";
	    }
	    // Создание придаточного объекта с объектами внутри 
	    
	    if (rootObject.has("market_cap") && rootObject.getAsJsonObject("market_cap").isJsonNull() == false) {
		    JsonObject market_cap = rootObject.getAsJsonObject("market_cap");
		    USD_market_cap = "\nКапитализация - "+market_cap.get("usd").getAsString()+"$"+" _("+market_cap.get("rub").getAsString()+" руб.)_\n";
		    }
	    double longCeff = 0;
	    double shortCeff = 0;
	    if (rootObject.has("sentiment_votes_up_percentage") && rootObject.get("sentiment_votes_up_percentage").isJsonNull() == false) {
	    	longCeff = rootObject.get("sentiment_votes_up_percentage").getAsDouble();
	    }
	    
	    if (rootObject.has("sentiment_votes_down_percentage") && rootObject.get("sentiment_votes_down_percentage").isJsonNull() == false) {
	    	shortCeff = rootObject.get("sentiment_votes_down_percentage").getAsDouble();
	    }
	    
	    if(rootObject.has("platforms") && rootObject.getAsJsonObject("platforms").isJsonNull() == false) {
	    JsonObject adreses  = rootObject.getAsJsonObject("platforms");
	    
	    if(adreses.has("ethereum") && adreses.getAsJsonPrimitive("ethereum").isJsonNull() == false) 
	    	ethereumAdress = "\n*ETH* Адрес: `"+adreses.get("ethereum").getAsString()+"`\n";
	    
		if(adreses.has("binance-smart-chain") && adreses.getAsJsonPrimitive("binance-smart-chain").isJsonNull() == false) 
		   binance_smart_chain = "\n*BSC* Адрес: `"+adreses.get("binance-smart-chain").getAsString()+"`\n";
		
		if(adreses.has("solana") && adreses.getAsJsonPrimitive("solana").isJsonNull() == false) 
			   SOL = "\n*SOL* Адрес: `"+adreses.get("solana").getAsString()+"`\n";
		
		if(adreses.has("polygon-pos") && adreses.getAsJsonPrimitive("polygon-pos").isJsonNull() == false) 
			   Matic = "\n*MATIC* Адрес: `"+adreses.get("polygon-pos").getAsString()+"`\n";
		
		if(adreses.has("tron") && adreses.getAsJsonPrimitive("tron").isJsonNull() == false) 
			   TRON = "\n*TRON* Адрес: `"+adreses.get("tron").getAsString()+"`\n";
		
		if(adreses.has("zilliqa") && adreses.getAsJsonPrimitive("zilliqa").isJsonNull() == false) 
			   ZIL = "\n*ZIL* Адрес: `"+adreses.get("zilliqa").getAsString()+"`\n";
		
		if(adreses.has("terra") && adreses.getAsJsonPrimitive("terra").isJsonNull() == false) 
			   TERRA = "\n*TERRA* Адрес: `"+adreses.get("terra").getAsString()+"`\n";
		
		if(adreses.has("algorand") && adreses.getAsJsonPrimitive("algorand").isJsonNull() == false) 
			   ALGO = "\n*ALGO* Адрес: `"+adreses.get("algorand").getAsString()+"`\n";
		
		if(adreses.has("near-protocol") && adreses.getAsJsonPrimitive("near-protocol").isJsonNull() == false) 
			   NEAR = "\n*NEAR* Адрес: `"+adreses.get("near-protocol").getAsString()+"`\n";
		
		if(adreses.has("polkadot") && adreses.getAsJsonPrimitive("polkadot").isJsonNull() == false) 
			   DOT = "\n*DOT* Адрес: `"+adreses.get("polkadot").getAsString()+"`\n";
		
		if(adreses.has("arbitrum-one") && adreses.getAsJsonPrimitive("arbitrum-one").isJsonNull() == false) 
			   ARB_ONE = "\n*ARB-ONE* Адрес: `"+adreses.get("arbitrum-one").getAsString()+"`\n";
		
		if(adreses.has("fantom") && adreses.getAsJsonPrimitive("fantom").isJsonNull() == false) 
			   FANTOM = "\n*FANTOM* Адрес: `"+adreses.get("fantom").getAsString()+"`\n";
		
		if(adreses.has("osmosis") && adreses.getAsJsonPrimitive("osmosis").isJsonNull() == false) 
			   OSMO = "\n*OSMO* Адрес: `"+adreses.get("osmosis").getAsString()+"`\n";
		
		if(adreses.has("avalanche") && adreses.getAsJsonPrimitive("avalanche").isJsonNull() == false) 
			   AVAX = "\n*AVAX* Адрес: `"+adreses.get("avalanche").getAsString()+"`\n";
		
		if(adreses.has("optimistic-ethereum") && adreses.getAsJsonPrimitive("optimistic-ethereum").isJsonNull() == false) 
			   OPT_ETH = "\n*OPT-ETH* Адрес: `"+adreses.get("optimistic-ethereum").getAsString()+"`\n";
		
		if(adreses.has("boba") && adreses.getAsJsonPrimitive("boba").isJsonNull() == false) 
			   BOBA = "\n*BOBA* Адрес: `"+adreses.get("boba").getAsString()+"`\n";
		
		if(adreses.has("celo") && adreses.getAsJsonPrimitive("celo").isJsonNull() == false) 
			   CELO = "\n*CELO* Адрес: `"+adreses.get("celo").getAsString()+"`\n";
		
		if(adreses.has("moonbeam") && adreses.getAsJsonPrimitive("moonbeam").isJsonNull() == false) 
			   BEAM = "\n*BEAM* Адрес: `"+adreses.get("moonbeam").getAsString()+"`\n";
		
		if(adreses.has("oasis") && adreses.getAsJsonPrimitive("oasis").isJsonNull() == false) 
			   OASIS = "\n*OASIS* Адрес: `"+adreses.get("oasis").getAsString()+"`\n";

	    }
	    
	    if (rootObject.has("links") && rootObject.getAsJsonObject("links").isJsonNull() == false) {
	    JsonObject links = rootObject.getAsJsonObject("links");
		    if (links.has("homepage") && links.getAsJsonArray("homepage").isJsonNull() == false) {
		    JsonElement WebSite = links.get("homepage").getAsJsonArray().get(0);
		    link = WebSite.toString();
		    StringBuilder site = new StringBuilder(link);
		    site.deleteCharAt(0);
		    site.deleteCharAt(site.length()-1);
		    link = "\nСайт монеты - "+site.toString()+"\n";
		    }
	    }
	    
	    JsonObject market_dates = rootObject.getAsJsonObject("market_data");
	    JsonObject min_price = market_dates.getAsJsonObject("low_24h");
	    JsonObject max_price = market_dates.getAsJsonObject("high_24h");
	    JsonObject currect_price = market_dates.getAsJsonObject("current_price");
	    
	    String price_change_percentage_24h = "";
	    String price_change_percentage_7d = "";
	    String price_change_percentage_14d = "";
	    String price_change_percentage_30d = "";
	    String max = max_price.get("usd").getAsString();
	    String min = min_price.get("usd").getAsString();
	    // Объект внутри 
	    if (market_dates.has("price_change_percentage_24h") && market_dates.get("price_change_percentage_24h").isJsonNull() == false) {
	    	if (market_dates.get("price_change_percentage_24h").getAsDouble() <= 0)
	    		price_change_percentage_24h ="\n🔻 *24ч "+df.format(market_dates.get("price_change_percentage_24h").getAsDouble())+"%*  ";
	    	else
	    		price_change_percentage_24h ="\n❇️ *24ч "+df.format(market_dates.get("price_change_percentage_24h").getAsDouble())+"%*  ";
	    }
	    
	    if (market_dates.has("price_change_percentage_7d") && market_dates.get("price_change_percentage_7d").isJsonNull() == false) {
	    	if (market_dates.get("price_change_percentage_7d").getAsDouble() <= 0)
	    		price_change_percentage_7d = "🔻 *7д "+df.format(market_dates.get("price_change_percentage_7d").getAsDouble())+"%*  ";
	    	else 
	    		price_change_percentage_7d = "❇️ *7д "+df.format(market_dates.get("price_change_percentage_7d").getAsDouble())+"%*  ";
	    }
	    
	    if (market_dates.has("price_change_percentage_14d") && market_dates.get("price_change_percentage_14d").isJsonNull() == false) {
	    	if(market_dates.get("price_change_percentage_14d").getAsDouble() <= 0)
	    		price_change_percentage_14d = "\n🔻 *14д "+df.format(market_dates.get("price_change_percentage_14d").getAsDouble())+"%*  ";
	    	else 
	    		price_change_percentage_14d = "\n❇️ *14д "+df.format(market_dates.get("price_change_percentage_14d").getAsDouble())+"%*  ";
	    }
	    
	    if (market_dates.has("price_change_percentage_30d") && market_dates.get("price_change_percentage_30d").isJsonNull() == false) {
	    	if(market_dates.get("price_change_percentage_30d").getAsDouble() <= 0)
	    		price_change_percentage_30d = "🔻 *30д "+df.format(market_dates.get("price_change_percentage_30d").getAsDouble())+"%*  \n";
	    	else
	    		price_change_percentage_30d = "❇️ *30д "+df.format(market_dates.get("price_change_percentage_30d").getAsDouble())+"%*  \n";
	    }
	    
	    String price = "\nЦена - *"+currect_price.get("usd").getAsString()+"$* _("+currect_price.get("rub").getAsString()+" руб.)_ \n🔻 *"+min+"$ | "+max+"$* ❇️\n"
	    		+ "\n🐮 *"+longCeff+"% 🐮 X 🐻 "+shortCeff+"%* 🐻\n";
	    String name = "*Информация по монете \n"+rootObject.get("name").getAsString()+" $"+rootObject.get("symbol").getAsString().toUpperCase()+" от @u_bablo*\n";
	    
	    text = 	name + ecosistems + link + price + price_change_percentage_24h + price_change_percentage_7d + price_change_percentage_14d + 
	    		price_change_percentage_30d + USD_market_cap + ethereumAdress + binance_smart_chain + SOL + Matic + TRON + ZIL + TERRA + ALGO + 
	    		NEAR + DOT + ARB_ONE + FANTOM + OSMO + AVAX + OPT_ETH + BOBA + CELO + BEAM + OASIS;
		}
	    return text;
	    	
		}
	public String API_CoinGeko_coin_description (String coin) throws IOException {
		String text = "";
		String enDescription = "";
		String link = "";
		String twitter = "";
		String telegram = "";
		String market_cap_rank = "";
		
		URL url = new URL("https://api.coingecko.com/api/v3/coins/"+coin+"");
		URLConnection connection =  url.openConnection();
				
		JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream()));
		JsonElement jsonElement = JsonParser.parseReader(json);
		
		// Создание главного объекта
	    JsonObject rootObject = jsonElement.getAsJsonObject();
	    String name = "\n*Описание "+rootObject.get("name").getAsString()+" :* \n";
	    
	    if (rootObject.get("market_cap_rank").isJsonNull() == false) {
	    market_cap_rank = "Место по капитализации - _"+rootObject.get("market_cap_rank").getAsString()+"_\n";
	    }
	    
	    if (rootObject.has("description") && rootObject.getAsJsonObject("description").isJsonNull() == false) {
	        JsonObject descriptions = rootObject.getAsJsonObject("description");
	    	    if (descriptions.has("en")) {
	    	    	// Markdown //
	    	    	if (new StringBuilder(descriptions.get("en").getAsString()).indexOf("href") == -1) {
	    	    	enDescription = "\n_"+descriptions.get("en").getAsString()+"_\n";
	    	    	}
	    	    	// HTML //
	    	    	else {
	    	    		enDescription = "\n"+new CopyDown().convert(descriptions.get("en").getAsString())+"\n";
	    	    	}
	    	    }
	        }
	        
	        if (rootObject.has("links") && rootObject.getAsJsonObject("links").isJsonNull() == false) {
	        JsonObject links = rootObject.getAsJsonObject("links");
	    	    if (links.has("homepage")) {
	    	    JsonElement WebSite = links.get("homepage").getAsJsonArray().get(0);
	    	    link = WebSite.toString();
	    	    StringBuilder site = new StringBuilder(link);
	    	    site.deleteCharAt(0);
	    	    site.deleteCharAt(site.length()-1);
	    	    link = site.toString();
	    	    }
	        }
	        if(rootObject.has("community_data") && rootObject.getAsJsonObject("community_data").isJsonNull() == false) {
	            JsonObject community_data = rootObject.getAsJsonObject("community_data");
	            if (community_data.has("twitter_followers") && community_data.get("twitter_followers").isJsonNull() == false && 
	            		community_data.get("twitter_followers").getAsString().equals("0") == false)
	            twitter = "\nTwitter - *"+community_data.get("twitter_followers").getAsString()+"*\n";
	            
	            if (community_data.has("telegram_channel_user_count") && community_data.get("telegram_channel_user_count").isJsonNull() == false && 
	            		community_data.get("telegram_channel_user_count").getAsString().equals("0") == false) 
	    	        telegram = "\nTelegram - *"+community_data.get("telegram_channel_user_count").getAsString()+"*\n";
	            }
	    
		text =    market_cap_rank
				+ name
				+ enDescription
				+ twitter
				+ telegram
	    		+ "\n*| Спонсор @u_bablo |*";
		return text;
		
	}
	public ArrayList<String> API_CoinGeko_coin_markets (String coin) throws IOException {
		ArrayList<String> markets = new ArrayList<String>();
		URL url = new URL("https://api.coingecko.com/api/v3/coins/"+coin+"");
		URLConnection connection =  url.openConnection();
				
		JsonReader json = new JsonReader(new InputStreamReader(connection.getInputStream()));
		JsonElement jsonElement = JsonParser.parseReader(json);
		
		// Создание главного объекта
	    JsonObject rootObject = jsonElement.getAsJsonObject();
	    JsonArray tickers = rootObject.getAsJsonArray("tickers");
	    
	    for (int i = 0; i<tickers.size(); i++) {
	    	String trade_url = "";
	    	String base = "";
	    	String target = "";
	    	JsonObject tickersObject = tickers.get(i).getAsJsonObject();
	    	
	    	if(tickersObject.get("trade_url").isJsonNull() == false) {
	    	trade_url = tickersObject.get("trade_url").getAsString();
	    	base = tickersObject.get("base").getAsString();
	    	target = tickersObject.get("target").getAsString();
	    	}
	    	
	    	JsonObject market = tickersObject.getAsJsonObject("market");
	    	int volume = tickersObject.get("volume").getAsInt();
		    	if (volume != 0 && tickersObject.get("volume").getAsString().equals("") == false) {
			    	String CurrencyName = market.get("name").getAsString();
			    	if(trade_url.equals("") == false && target.startsWith("U"))
			    		markets.add(volume+","+CurrencyName+"-"+trade_url+"| $"+base+" / $"+target+" |");
	    	}
	    }
	    markets.sort(
	    	    Comparator.<String>comparingInt(s -> Integer.parseInt(
	    	        s.substring(0, s.indexOf(","))
	    	    ))
	    	    .reversed()
	    	);
	    return markets;
	}
}

