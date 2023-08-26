package com.github.malyshevhen.command


import com.github.malyshevhen.command.tasks.VideoFileSearchTask
import com.github.malyshevhen.util.markdown.DatabaseConstructor
import com.github.malyshevhen.util.markdown.FileUtil
import com.github.malyshevhen.util.markdown.CardConstructor
import com.github.malyshevhen.util.screenshot.ScreenshotUtil
import picocli.CommandLine.Command
import picocli.CommandLine.Option

import static com.github.malyshevhen.util.markdown.CardConstructor.CardType.MODERN
import static picocli.CommandLine.Help.Ansi.AUTO as ansi

@Command(name = 'init',
        description = 'Find all video in given directory, and create Obsidian folder DB.',
        mixinStandardHelpOptions = true)
class InitCommand implements Runnable {

    @Option(names = ['-i', '--input-folder'],
            description = 'Input folder path. Default: current folder')
    File inputFolder = new File(System.getProperty('user.dir'))

    @Override
    void run() {
        println ansi.string('@|bold,fg(green) Scanning directories... |@')

        def videoFiles = scan()

        println ansi.string('@|bold,fg(green) Creating screenshots... |@')

        def pictures = videoFiles.stream()
                .map(ScreenshotUtil::takeScreenshots)
                .toList()

        println ansi.string('@|bold,fg(green) Formatting markdown files... |@')

        def mds = pictures.stream()
                .map(it -> {
                    def markdownUtil = new CardConstructor(it)
                    return markdownUtil.buildCard(MODERN)
                }).toList()

        println ansi.string('@|bold,fg(green) Saving markdown files... |@')

        mds.each { FileUtil::save(it) }

        println ansi.string('@|bold,fg(green) Complete... |@')

        initDbFile()
    }

    private List<File> scan() {
        def videoFileSearchTask = new VideoFileSearchTask(inputFolder as File)
        return videoFileSearchTask.findVideoFiles()
    }

    private void initDbFile() {
        def markdownUtil = new DatabaseConstructor()
        markdownUtil.createDatabase(inputFolder)
    }

}
