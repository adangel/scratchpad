/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package npe.with.methodresolution;

public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);
    
    void addConverter(GenericConverter converter);
}
