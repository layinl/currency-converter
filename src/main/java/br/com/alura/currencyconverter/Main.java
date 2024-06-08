package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.model.Currency;
import br.com.alura.currencyconverter.resource.ExchangeRateAPIRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    Currency currency;
    Scanner scan = new Scanner(System.in);
    try {
      currency = gson
        .fromJson(ExchangeRateAPIRequest.get(scan.next()), Currency.class);
      System.out.println(gson.toJson(currency));
      System.out.println(currency.convert(scan.nextDouble(), "USD"));
    } catch (JsonSyntaxException e) {
      System.out.println("Por favor, digite um valor válido");
    }


  }


}