package br.com.alura.currencyconverter.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record Currency(
  @SerializedName("base_code")
  String code,
  @SerializedName("conversion_rates")
  Map<String, Double> conversionRates
) {

}