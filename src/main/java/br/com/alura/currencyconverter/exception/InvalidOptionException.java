package br.com.alura.currencyconverter.exception;

public class InvalidOptionException extends RuntimeException {

  public InvalidOptionException() {
    super("Por favor, digite uma opção válida");
  }

  public InvalidOptionException(String message) {
    super(message);
  }
}
