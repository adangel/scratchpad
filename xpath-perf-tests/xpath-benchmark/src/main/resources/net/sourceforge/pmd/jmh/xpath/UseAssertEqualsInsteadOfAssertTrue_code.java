/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

public class FooTest extends TestCase {

    void testCode() {
        Object a, b;
        assertTrue(a.equals(b)); // bad usage
        assertEquals("a should equals b", a, b); // good usage
    }
}
