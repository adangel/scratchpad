package com.github.pmd.issue1330;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        // We reference here PageContext
        MyPageContext pageContext = null;
        pageContext.pushBody().append("Test");

        MyService service = null;
    }
}
