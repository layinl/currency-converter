package br.com.alura.currencyconverter;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {

  public static void main(String[] args) {
    System.out.println(Dotenv.load().get("WELCOME"));
  }


}