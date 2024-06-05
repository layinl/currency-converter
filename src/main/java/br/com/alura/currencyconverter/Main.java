package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.model.Currency;
import br.com.alura.currencyconverter.resource.ExchangeRateAPIRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    Currency currency = gson
      .fromJson(ExchangeRateAPIRequest.get("BRL"), Currency.class);
    JsonObject currencyTree = gson.toJsonTree(currency).getAsJsonObject();
    System.out.println(currencyTree);
  }


}