package com.mine.codegen;

import com.mine.codegen.cmd.ConfigHelp;
import com.mine.codegen.cmd.Generate;
import com.mine.codegen.cmd.Langs;
import com.mine.codegen.cmd.Validate;
import com.mine.codegen.cmd.Version;
import io.airlift.airline.Cli;
import io.airlift.airline.Help;

/**
 * User: lanwen Date: 24.03.15 Time: 17:56
 * <p>
 * Command line interface for swagger codegen use `swagger-codegen-cli.jar help` for more info
 *
 * @since 2.1.3-M1
 */
public class CXFCodegen {


    public static void main(String[] args) {
        String version = Version.readVersionFromResources();
        @SuppressWarnings("unchecked")
        Cli.CliBuilder<Runnable> builder =
                Cli.<Runnable>builder("swagger-codegen-cli")
                        .withDescription(
                                String.format(
                                        "Swagger code generator CLI (version %s). More info on swagger.io",
                                        version))
                        .withDefaultCommand(Langs.class)
                        .withCommands(Generate.class, Langs.class, Help.class,
                                ConfigHelp.class, Validate.class, Version.class);

        builder.build().parse(args).run();
    }
}
