package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.exception.CurrencyNotAllowedException;
import br.com.alura.currencyconverter.exception.CurrencyNotFoundException;
import br.com.alura.currencyconverter.model.Currency;
import br.com.alura.currencyconverter.resource.CurrencyCodeOp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {

  public static void main() {
    Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();
    Currency currency;
    Scanner scan = new Scanner(System.in);
    String firstCode = "", secondCode = "";
    int option = 0;
    Map<String, Currency> currencies = new TreeMap<>();

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
      do {
        try {
          option = scan.nextInt();
          if (option == -1) return;
          firstCode = CurrencyCodeOp.getCode(option);
        } catch (InputMismatchException e) {
          System.out.println("Por favor, digite um número inteiro das opções");
        }
      } while (option < 1 || option > 6);

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
      do {
        try {
          option = scan.nextInt();
          if (option == -1) return;
          secondCode = CurrencyCodeOp.getCode(option);
        } catch (InputMismatchException e) {
          System.out.println("Por favor, digite um número inteiro das opções");
        }
      } while (option < 1 || option > 6);

      if (!currencies.containsKey(firstCode)) {
        currency = gson
          .fromJson(getfromAPI(firstCode), Currency.class);
        currencies.put(firstCode, currency);
      } else {
        currency = currencies.get(firstCode);
      }
      System.out.println("Qual o valor?");
      do {
        try {
          System.out.println(STR."\{firstCode} em \{secondCode}: \{currency.convert(scan.nextDouble(), secondCode)}");
          break;
        } catch (InputMismatchException e) {
          System.out.println("Por favor, digite um número");
          scan.next(); // had to use this because of this stupid input buffering
        } catch (JsonSyntaxException e) {
          System.out.println("Por favor, digite um valor válido");
          scan.next();// had to use this because of this stupid input buffering
        }
      } while (true); // don't worry. This loop will end eventually
      System.out.println("Deseja converter mais? Digite -1 para encerrar");
      try {
        option = scan.nextInt();
      } catch (InputMismatchException e) {
        scan.next(); // mas que raiva desse scan...
      }
    } while (option != -1);
    System.out.println(
      """
      Obrigado por utilizar o Conversor de Moedas!
      Criado por layinl
      """
    );
  }

  /**
   * Sends an API request to <a href="https://v6.exchangerate-api
   * .com/">Exchange Rate API</a>, fetching and returning the currency's
   * data as JSON
   * @param currency the currency code to search
   * @return the currency data as JSON
   */
  private static String getfromAPI(final String currency) {
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(STR."https://v6.exchangerate-api.com/v6/\{Dotenv.load().get("API_KEY")}/latest/\{currency}"))
        .build();
      HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());
//      if (response.body().contains("unsupported-code"))
//        throw new CurrencyNotFoundException();
//      CurrencyCodeOp.isValid(currency);
      return response.body();
    } catch (CurrencyNotAllowedException | CurrencyNotFoundException e) {
      System.out.println(STR."Ocorreu um erro na moeda inserida: \{e.getMessage()}");
    } catch (IOException | InterruptedException e) {
      System.out.println(STR."Ocorreu um erro na requisição: \{e.getMessage()}");
    } catch (Exception e) {
      System.out.println(STR."Ocorreu um erro inesperado: \{e.getMessage()}");
    }
    // idk
    return "error";
  }

}