package com.github.malyshevhen

import com.github.malyshevhen.scan.ScanCommand
import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(name = 'odabagen',
        description = 'Application for generating video catalog in Obsidian and DB folder plugin',
        mixinStandardHelpOptions = true,
        subcommands = [ScanCommand])
class OdabagenGroovyCommand implements Runnable {

    @Option(names = ['-v', '--verbose'], description = '...')
    boolean verbose

    static void main(String[] args) throws Exception {
        PicocliRunner.run(OdabagenGroovyCommand.class, args)
    }

    void run() {
        // business logic here
        if (verbose) {
            println "Hi!"
        }
    }
}
