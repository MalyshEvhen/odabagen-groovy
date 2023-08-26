package com.github.malyshevhen.options

import picocli.CommandLine.Option

class GlobalOptions {

    @Option(names = ['-v', '--verbose'])
    private boolean verbose

    boolean isVerbose() {
        return verbose
    }
}
