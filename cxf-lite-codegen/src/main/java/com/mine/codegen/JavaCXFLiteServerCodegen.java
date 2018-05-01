package com.mine.codegen;

import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.AbstractJavaJAXRSServerCodegen;
import io.swagger.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JavaCXFLiteServerCodegen extends AbstractJavaJAXRSServerCodegen {

    public JavaCXFLiteServerCodegen() {
        super();

        sourceFolder = "src/main/java";
        implFolder = "../service/src/main/java";

        apiTemplateFiles.put("apiServiceImpl.mustache", ".java");
        modelDocTemplateFiles.remove("model_doc.mustache");
        apiDocTemplateFiles.remove("api_doc.mustache");
        apiTestTemplateFiles.remove("api_test.mustache");

        templateDir = "jaxrs-cxf-lite";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        supportingFiles.clear();
    }

    @Override
    public boolean shouldOverwrite(String filename) {
        return !filename.endsWith("Impl.java");
    }

    @Override
    public String getName() {
        return "jaxrs-cxf-lite";
    }

    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        super.addOperationToGroup(tag, resourcePath, operation, co, operations);
        co.subresourceOperation = !co.path.isEmpty();
    }

    @Override
    public void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
        super.postProcessModelProperty(model, property);
        model.imports.remove("ApiModelProperty");
        model.imports.remove("ApiModel");
        model.imports.remove("JsonSerialize");
        model.imports.remove("ToStringSerializer");
    }

    @Override
    public String getHelp() {
        return "Generates a Java JAXRS Server(lite version) application based on Apache CXF framework.";
    }

}
