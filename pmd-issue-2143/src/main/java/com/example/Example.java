package com.example;

import java.util.concurrent.atomic.LongAdder;
import java.util.Map;

public class Example {
    
    
  public static void main(String... args) {
      Example ex = new Example();
      SolrInputDocument autofilldocument = new SolrInputDocument();
      Document doc = new DocImpl();
      ex.addProducts(autofilldocument, doc, 5);
  }

  private int addProducts(SolrInputDocument autofillDocument,
                        Document doc,
                        int rows) {
    var rowAdder = new LongAdder();
    rowAdder.add(rows);

    processDocument(autofillDocument,
                    doc.getRecord(),
                    rowAdder);
    return rowAdder.intValue();
  }

  private void processDocument(SolrInputDocument autofillDocument,
                             Object docMap,
                             LongAdder rowAdder) {
    System.out.println("Processing " + autofillDocument + " " + docMap + " " + rowAdder);
  }

  private void processDocument(SolrInputDocument autofillDocument,
                             Map<String, Object> docMap,
                             LongAdder rowAdder) {
     System.out.println("Processing " + autofillDocument + " " + docMap + " " + rowAdder);
  }
  
  private void myUnusedMethod() {}
}
