package com.mine.util;

import io.swagger.models.*;
import io.swagger.models.auth.SecuritySchemeDefinition;
import io.swagger.models.parameters.Parameter;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class SwaggerCombiner extends Swagger {

    public SwaggerCombiner combine(Swagger rhs) {
        this.swagger = rhs.getSwagger();
        this.info = rhs.getInfo();
        this.host = rhs.getHost();
        this.basePath = rhs.getBasePath();

        if (!CollectionUtils.isEmpty(rhs.getTags())) {
            for (final Tag tag : rhs.getTags()) {
                this.addTag(tag);
            }
        }

        if (!CollectionUtils.isEmpty(rhs.getSchemes())) {
            for (final Scheme scheme : rhs.getSchemes()) {
                this.addScheme(scheme);
            }
        }

        if (!CollectionUtils.isEmpty(rhs.getConsumes())) {
            for (String consume : rhs.getConsumes()) {
                this.addConsumes(consume);
            }
        }

        if (!CollectionUtils.isEmpty(rhs.getProduces())) {
            for (String produce : rhs.getProduces()) {
                this.addConsumes(produce);
            }
        }

        if (!CollectionUtils.isEmpty(rhs.getSecurity())) {
            for (final SecurityRequirement securityRequirement : rhs.getSecurity()) {
                this.addSecurity(securityRequirement);
            }
        }

        if (null == this.paths) {
            this.paths = new LinkedHashMap<>();
        }
        if (null != rhs.getPaths()) {
            for (Map.Entry<String, Path> pathEntry : rhs.getPaths().entrySet()) {
                this.paths.put(pathEntry.getKey(), pathEntry.getValue());
            }
        }

        if (null == this.securityDefinitions) {
            this.securityDefinitions = new LinkedHashMap<>();
        }
        if (null != rhs.getSecurityDefinitions()) {
            for (Map.Entry<String, SecuritySchemeDefinition> securitySchemeDefinitionEntry : rhs.getSecurityDefinitions().entrySet()) {
                this.securityDefinitions.put(securitySchemeDefinitionEntry.getKey(), securitySchemeDefinitionEntry.getValue());
            }
        }

        if (null == this.definitions) {
            this.definitions = new LinkedHashMap<>();
        }
        if (null != rhs.getDefinitions()) {
            for (Map.Entry<String, Model> modelEntry : rhs.getDefinitions().entrySet()) {
                this.definitions.put(modelEntry.getKey(), modelEntry.getValue());
            }
        }

        if (null == this.parameters) {
            this.parameters = new LinkedHashMap<>();
        }
        if (null != rhs.getParameters()) {
            for (Map.Entry<String, Parameter> parameterEntry : rhs.getParameters().entrySet()) {
                this.addParameter(parameterEntry.getKey(), parameterEntry.getValue());
            }
        }

        if (null == this.responses) {
            this.responses = new LinkedHashMap<>();
        }
        if (null != rhs.getResponses()) {
            for (Map.Entry<String, Response> responseEntry : rhs.getResponses().entrySet()) {
                this.responses.put(responseEntry.getKey(), responseEntry.getValue());
            }
        }

        return this;
    }
}
