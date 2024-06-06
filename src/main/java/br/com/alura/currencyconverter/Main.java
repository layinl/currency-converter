package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.model.Currency;
import br.com.alura.currencyconverter.resource.ExchangeRateAPIRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    Currency currency = gson
      .fromJson(ExchangeRateAPIRequest.get("BRL"), Currency.class);

    System.out.println(gson.toJson(currency));
    System.out.println(currency.convert(10.0, "ARS"));


  }


}