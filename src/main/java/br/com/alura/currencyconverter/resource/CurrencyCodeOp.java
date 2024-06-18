package br.com.alura.currencyconverter.resource;

import br.com.alura.currencyconverter.exception.CurrencyNotAllowedException;
import br.com.alura.currencyconverter.exception.InvalidOptionException;

import java.util.regex.Pattern;

/**
 * I'm not too creative with names today...
 */
public class CurrencyCodeOp {

  /**
   * Checks if the currency code is either ARS, BOB, CLS, BRL, COP or
   * USD
   * @param currency the currency code to be validated
   * @throws CurrencyNotAllowedException if the currency code isn't one
   * of the valid codes
   * @deprecated Because of the switch statement control, this might not
   * be needed for now.
   */
  public static void isValid(String currency) {
    Pattern pattern = Pattern.compile("ARS|BOB|BRL|CLP|COP|USD");
    if (!pattern.matcher(currency.toUpperCase()).matches())
      throw new CurrencyNotAllowedException();
  }

  /**
   * Returns the currency code according to the option:
   * <ol>
   *   <li>USD</li>
   *   <li>BRL</li>
   *   <li>ARS</li>
   *   <li>BOB</li>
   *   <li>CLP</li>
   *   <li>COP</li>
   * </ol>
   * @param option the option number to select
   * @return the corresponding currency code or an empty string
   * otherwise
   */
  public static String getCode(int option) {
    try {
      return switch (option) {
        case 1 -> "USD";
        case 2 -> "BRL";
        case 3 -> "ARS";
        case 4 -> "BOB";
        case 5 -> "CLP";
        case 6 -> "COP";
        default -> throw new InvalidOptionException();
      };
    } catch (InvalidOptionException e) {
      System.out.println(e.getMessage());
    }
    return "";
  }

}
