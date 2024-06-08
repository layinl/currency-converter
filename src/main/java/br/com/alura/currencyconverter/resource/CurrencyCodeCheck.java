package br.com.alura.currencyconverter.resource;

import br.com.alura.currencyconverter.exception.CurrencyNotAllowedException;

import java.util.regex.Pattern;

public class CurrencyCodeCheck {

  public static boolean isValid(String currency) {
    Pattern pattern = Pattern.compile("ARS|BOB|BRL|CLP|COP|USD");
    if (!pattern.matcher(currency.toUpperCase()).matches())
      throw new CurrencyNotAllowedException();
    return true;
  }

}
