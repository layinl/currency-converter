package br.com.alura.currencyconverter.exception;

public class CurrencyNotAllowedException extends RuntimeException {

  public CurrencyNotAllowedException() {
    super("Moeda não suportada. O sistema só suporta conversão das moedas ARS, BOB, CLS, BRL, COP e USD");
  }

  public CurrencyNotAllowedException(String message) {
    super(message);
  }
}
