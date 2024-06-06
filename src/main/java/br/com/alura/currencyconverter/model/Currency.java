package br.com.alura.currencyconverter.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

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

  public Double convert(Double amount, String currencyCode) {
    return Math.round(amount * conversionRates.get(currencyCode) * 100) / 100.0;
  }

}