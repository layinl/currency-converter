package br.com.alura.currencyconverter.exception;

public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException() {
    super("Essa moeda não existe");
  }

  public CurrencyNotFoundException(String message) {
    super(message);
  }

}
