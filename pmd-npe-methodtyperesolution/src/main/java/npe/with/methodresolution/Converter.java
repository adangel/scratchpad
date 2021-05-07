/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package npe.with.methodresolution;

public interface Converter<S, T> {

    T convert(S source);
}
