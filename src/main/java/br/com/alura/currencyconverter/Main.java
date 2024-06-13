package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.model.Currency;
import br.com.alura.currencyconverter.resource.CurrencyCodeOp;
import br.com.alura.currencyconverter.resource.ExchangeRateAPIRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main() {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    Currency currency;
    Scanner scan = new Scanner(System.in);
    String firstCode = "", secondCode = "";
    int option = 0;

    System.out.println("Bem vindo ao conversor de moedas!");
    do {
      System.out.println("""
        Selecione a moeda:
        1. USD
        2. BRL
        3. ARS
        4. BOB
        5. CLP
        6. COP
        Digite -1 para sair
        """
      );
      try {
        option = scan.nextInt();
        if (option == -1) return;
        firstCode = CurrencyCodeOp.getCode(option);
      } catch (InputMismatchException e) {
        System.out.println("Por favor, digite um número inteiro das opções");
      }
    } while (option < 1 || option > 6);

    do {
      System.out.println("""
        Selecione a moeda para converter:
        1. USD
        2. BRL
        3. ARS
        4. BOB
        5. CLP
        6. COP
        Digite -1 para sair
        """
      );
      try {
        option = scan.nextInt();
        if (option == -1) return;
        secondCode = CurrencyCodeOp.getCode(option);
      } catch (InputMismatchException e) {
        System.out.println("Por favor, digite um número inteiro das opções");
      }
    } while (option < 1 || option > 6);

    try {
      currency = gson
        .fromJson(ExchangeRateAPIRequest.get(firstCode), Currency.class);
      System.out.println("Qual o valor?");
      System.out.println(STR."\{firstCode} em \{secondCode}: \{currency.convert(scan.nextDouble(), secondCode)}");
    } catch (InputMismatchException e) {
      System.out.println("Por favor, digite um número");
    } catch (JsonSyntaxException e) {
      System.out.println("Por favor, digite um valor válido");
    }
  }


}