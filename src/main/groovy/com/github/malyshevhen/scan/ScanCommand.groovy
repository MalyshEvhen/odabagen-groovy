package com.github.malyshevhen.scan

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.Directory
import com.drew.metadata.Metadata
import com.drew.metadata.Tag
import com.github.malyshevhen.options.GlobalOptions
import com.github.malyshevhen.scan.tasks.VideoFileSearchTask
import com.github.malyshevhen.util.markdown.FileUtil
import com.github.malyshevhen.util.markdown.MarkdownUtil
import com.github.malyshevhen.util.screenshot.ScreenshotUtil
import picocli.CommandLine.Command
import picocli.CommandLine.Mixin
import picocli.CommandLine.Option

@Command(name = 'scan',
        description = 'Find all video in given directory.',
        mixinStandardHelpOptions = true)
class ScanCommand implements Runnable {

    public static DOUBLE_LINE_SEPARATOR = '========================================================'
    public static SINGLE_LINE_SEPARATOR = '--------------------------------------------------------'

    @Mixin
    GlobalOptions globalOptions

    @Option(names = ['-i', '--input-folder'],
            description = 'Input folder path. Default: current folder')
    File inputFolder = new File(System.getProperty('user.dir'))

    @Option(names = ['-db', '--create-db'])
    boolean createDb

    @Override
    void run() {
        def videoFileSearchTask = new VideoFileSearchTask(inputFolder as File)
        def videoFiles = videoFileSearchTask.findVideoFiles()

        if (globalOptions.isVerbose()) {
            println('Scan command run in verbose mode...')

            videoFiles.each { videoFile ->
                Metadata metadata = null

                try {
                    println DOUBLE_LINE_SEPARATOR
                    println "Get metadata from file: ${videoFile.getName()}"
                    println DOUBLE_LINE_SEPARATOR

                    metadata = ImageMetadataReader.readMetadata(videoFile)
                } catch (ImageProcessingException | IOException e) {
                    e.printStackTrace()
                }

                /* Extract and print common metadata. */

                if (metadata != null) printDirectories(metadata)
            }

            if (createDb) {
                def pictures = videoFiles
                        .stream()
                        .map(ScreenshotUtil::takeScreenshots)
                        .toList()

                def mds = pictures.stream().map(it -> {
                    def mdCreator = new MarkdownUtil(it)
                    return mdCreator.buildArchiveCard()
                }).toList()

                mds.each { FileUtil::save(it) }
            }
        } else println 'Scan command run not in verbose mode...'
    }

    private static void printDirectories(Metadata metadata) {

        int counter = 1

        metadata.getDirectories().each { Directory directory ->
            println SINGLE_LINE_SEPARATOR
            println "Directory: ${directory.getName()}"
            println SINGLE_LINE_SEPARATOR

            directory.getTags().each { Tag tag ->
                println "${counter++}. ${tag.getTagName()} | ${tag.getTagType()} | ${tag.getDescription()}"
            }
            counter = 0
        }

    }
}
