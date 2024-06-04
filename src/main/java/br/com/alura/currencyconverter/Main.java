package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.resource.ExchangeRateAPIRequest;

public class Main {

  public static void main(String[] args) {
    System.out.println(ExchangeRateAPIRequest.get("BRL"));
  }


}