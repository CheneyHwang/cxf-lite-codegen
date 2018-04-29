package com.mine.codegen;

import io.swagger.codegen.CliOption;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.AbstractJavaJAXRSServerCodegen;
import io.swagger.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static io.swagger.codegen.languages.features.BeanValidationExtendedFeatures.USE_BEANVALIDATION_FEATURE;
import static io.swagger.codegen.languages.features.CXFServerFeatures.ADD_CONSUMES_PRODUCES_JSON;
import static io.swagger.codegen.languages.features.CXFServerFeatures.USE_ANNOTATED_BASE_PATH;
import static io.swagger.codegen.languages.features.SpringFeatures.USE_SPRING_ANNOTATION_CONFIG;

public class JavaCXFLiteServerCodegen extends AbstractJavaJAXRSServerCodegen {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCXFLiteServerCodegen.class);

    protected boolean addConsumesProducesJson = true;

    protected boolean useAnnotatedBasePath = false;

    public JavaCXFLiteServerCodegen() {
        super();

        artifactId = "cxf-lite";
        outputFolder = "cxf-lite";

        apiTemplateFiles.put("apiServiceImpl.mustache", ".java");

        modelDocTemplateFiles.remove("model_doc.mustache");
        apiDocTemplateFiles.remove("api_doc.mustache");
        apiTestTemplateFiles.remove("api_test.mustache");

        typeMapping.put("date", "LocalDate");

        importMapping.put("LocalDate", "org.joda.time.LocalDate");

        templateDir = "jaxrs-cxf-lite";

        cliOptions.add(CliOption.newBoolean(USE_SPRING_ANNOTATION_CONFIG, "Use Spring Annotation Config"));

        cliOptions.add(CliOption.newBoolean(USE_BEANVALIDATION_FEATURE, "Use BeanValidation Feature"));

        cliOptions
                .add(CliOption.newBoolean(ADD_CONSUMES_PRODUCES_JSON, "Add @Consumes/@Produces Json to API interface"));

        cliOptions.add(CliOption.newBoolean(USE_ANNOTATED_BASE_PATH, "Use @Path annotations for basePath"));

    }

    @Override
    public void processOpts() {
        super.processOpts();

        if (additionalProperties.containsKey(ADD_CONSUMES_PRODUCES_JSON)) {
            this.setAddConsumesProducesJson(convertPropertyToBooleanAndWriteBack(ADD_CONSUMES_PRODUCES_JSON));
        }

        if (additionalProperties.containsKey(USE_ANNOTATED_BASE_PATH)) {
            boolean useAnnotatedBasePathProp = convertPropertyToBooleanAndWriteBack(USE_ANNOTATED_BASE_PATH);
            this.setUseAnnotatedBasePath(useAnnotatedBasePathProp);
        }

        supportingFiles.clear();
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

    public void setAddConsumesProducesJson(boolean addConsumesProducesJson) {
        this.addConsumesProducesJson = addConsumesProducesJson;
    }

    public void setUseAnnotatedBasePath(boolean useAnnotatedBasePath) {
        this.useAnnotatedBasePath = useAnnotatedBasePath;
    }

}
