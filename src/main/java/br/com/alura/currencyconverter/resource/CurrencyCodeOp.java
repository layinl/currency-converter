package br.com.alura.currencyconverter.resource;

import br.com.alura.currencyconverter.exception.CurrencyNotAllowedException;
import br.com.alura.currencyconverter.exception.InvalidOptionException;

import java.util.regex.Pattern;

/**
 * I'm not too creative with names today...
 */
public class CurrencyCodeOp {

  public static void isValid(String currency) {
    Pattern pattern = Pattern.compile("ARS|BOB|BRL|CLP|COP|USD");
    if (!pattern.matcher(currency.toUpperCase()).matches())
      throw new CurrencyNotAllowedException();
  }

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
    return "cu";
  }

}
