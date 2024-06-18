package br.com.alura.currencyconverter.exception;

/**
 * The CurrencyNotAllowedException is a runtime exception that occurs
 * when the currency is not either ARS, BOB, CLS, BRL, COP or USD
 * @deprecated This might not be needed for now because of the switch
 * statement in Main
 * @see CurrencyNotFoundException
 */
public class CurrencyNotAllowedException extends RuntimeException {

  public CurrencyNotAllowedException() {
    super("Moeda não suportada. O sistema só suporta conversão das moedas ARS, BOB, CLS, BRL, COP e USD");
  }

  public CurrencyNotAllowedException(String message) {
    super(message);
  }
}
