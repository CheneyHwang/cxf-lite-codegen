package com.mine.codegen;

import io.swagger.codegen.languages.JavaCXFServerCodegen;

public class JavaCXFLiteServerCodegen extends JavaCXFServerCodegen {

  public String getName() {
    return "jaxrs-cxf-lite";
  }
}