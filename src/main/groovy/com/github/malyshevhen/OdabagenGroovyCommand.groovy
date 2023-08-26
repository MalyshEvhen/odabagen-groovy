package com.github.malyshevhen

import com.github.malyshevhen.command.InitCommand
import com.github.malyshevhen.command.ScanCommand
import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command

@Command(name = 'odabagen',
        description = 'Application for generating video catalog in Obsidian and DB folder plugin',
        mixinStandardHelpOptions = true,
        subcommands = [InitCommand, ScanCommand])
class OdabagenGroovyCommand implements Runnable {

    static void main(String[] args) throws Exception {
        PicocliRunner.run(OdabagenGroovyCommand.class, args)
    }

    void run() {}
}
