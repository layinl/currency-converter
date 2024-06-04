package br.com.alura.currencyconverter.resource;

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
      return response.body();
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
    return "Error";
  }

}
