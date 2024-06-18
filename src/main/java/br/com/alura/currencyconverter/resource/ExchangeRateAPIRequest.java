package br.com.alura.currencyconverter.resource;

import br.com.alura.currencyconverter.exception.CurrencyNotAllowedException;
import br.com.alura.currencyconverter.exception.CurrencyNotFoundException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPIRequest {

  public static String get(final String currency) {
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
    // just a workaround for now
    return "error";
  }

}
