
package com.github.pmd.issue1330;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MyService extends Service {

    protected MyService(URL arg0, QName arg1) {
        super(arg0, arg1);
    }

    static class Foo {
    }
}
