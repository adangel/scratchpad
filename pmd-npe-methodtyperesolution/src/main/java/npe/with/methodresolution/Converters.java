/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package npe.with.methodresolution;

import javax.money.Monetary;

public class Converters {

    public static void register(ConverterRegistry registry) {
        registry.addConverter(new NumberToStringConverter());
        registry.addConverter(new MoneyToStringConverter());
    }
    
    private static class NumberToStringConverter implements Converter<Number, String> {
        @Override
        public String convert(Number source) {
            return source.toString();
        }
    }
    
    // https://repo1.maven.org/maven2/javax/money/money-api/1.1/money-api-1.1.jar
    private static class MoneyToStringConverter implements Converter<Monetary, String> {
        @Override
        public String convert(Monetary source) {
            return source.toString();
        }
    }
    
}
