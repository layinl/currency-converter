package br.com.alura.currencyconverter.exception;

/**
 * The CurrencyNotFoundException is a runtime exception that occurs
 * when the checked currency doesn't exist
 * @deprecated This might not be needed for now because of the switch
 * statement in Main
 * @see CurrencyNotFoundException
 */
public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException() {
    super("Essa moeda não existe");
  }

  public CurrencyNotFoundException(String message) {
    super(message);
  }

}
