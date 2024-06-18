package br.com.alura.currencyconverter.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * The currency model represents the currency created based on its
 * code and the respective conversion rates
 * @param code the currency code (like USD for dollar)
 * @param conversionRates
 */
public record Currency(
  @SerializedName("base_code")
  String code,
  @SerializedName("conversion_rates")
  Map<String, Double> conversionRates
) {

  public Currency(
    String code,
    Map<String, Double> conversionRates) {
    Pattern pattern = Pattern.compile("ARS|BOB|BRL|CLP|COP|USD");
    this.code = code;
    this.conversionRates = new TreeMap<>();
    conversionRates.keySet().forEach(key -> {
      if (pattern.matcher(key).matches()) this.conversionRates.put(key, conversionRates.get(key));
    });
  }

  /**
   * Converts the value to the target currency equivalent
   * @param amount the value to be converted
   * @param currencyCode the target currency code
   * @return the target currency's equivalent value, rounded
   */
  public Double convert(Double amount, String currencyCode) {
    return Math.round(amount * conversionRates.get(currencyCode) * 100) / 100.0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Currency currency = (Currency) o;
    return Objects.equals(code, currency.code);
  }

}