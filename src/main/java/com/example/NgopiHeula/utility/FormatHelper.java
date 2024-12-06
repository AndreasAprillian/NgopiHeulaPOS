package com.example.NgopiHeula.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatHelper {
    public static String FormatCurrency(BigDecimal value){
        Locale indo = new Locale("id","ID");
        return NumberFormat.getCurrencyInstance(indo).format(value);
    }
    public static BigDecimal TotalPriceOrder(BigDecimal price, Integer qty, Double discount){
        var totalDisc = DiscountFormater(price,discount);
        var result = price.multiply(new BigDecimal(qty)).subtract(totalDisc);
        return result;
    }
    public static BigDecimal DiscountFormater(BigDecimal price,Double discount){
        var result = price.multiply(new BigDecimal(discount).divide(new BigDecimal(100)));
        return result;
    }
    public static String FormatTanggal(LocalDateTime date, String pattern){
        Locale indonesia = new Locale("id", "ID");
        String result = DateTimeFormatter.ofPattern(pattern,indonesia).format(date);
        return result;
    }
}
