package com.github.malyshevhen.command

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.Directory
import com.drew.metadata.Metadata
import com.drew.metadata.Tag
import com.github.malyshevhen.command.tasks.VideoFileSearchTask
import picocli.CommandLine.Option
import picocli.CommandLine.Command

import static picocli.CommandLine.Help.Ansi.AUTO as ansi

@Command(name = 'scan',
        description = 'Find all video in given directory.',
        mixinStandardHelpOptions = true)
class ScanCommand implements Runnable {

    public static DOUBLE_LINE_SEPARATOR = '='.repeat(30)
    public static SINGLE_LINE_SEPARATOR = '-'.repeat(30)

    @Option(names = ['-i', '--input-folder'],
            description = 'Input folder path. Default: current folder')
    File inputFolder = new File(System.getProperty('user.dir'))

    @Override
    void run() {
        println ansi.string('@|bold,fg(green) Scanning directories... |@')

        def videoFiles = scan()

        if (dryRun) {
            videoFiles.each { videoFile ->
                try {
                    println ansi.string("@|bold,fg(yellow) $DOUBLE_LINE_SEPARATOR |@")
                    println ansi.string("@|bold,fg(yellow) " +
                            "Get metadata from file: ${videoFile.getName()} |@")
                    println ansi.string("@|bold,fg(yellow) $DOUBLE_LINE_SEPARATOR |@")

                    def metadata = ImageMetadataReader.readMetadata(videoFile)
                    printMetadata(metadata)
                } catch (ImageProcessingException | IOException e) {
                    e.printStackTrace()
                }
            }
        }
    }

    private static void printMetadata(Metadata metadata) {
        int counter = 1

        metadata.getDirectories().each { Directory directory ->
            println ansi.string("@|bold,fg(yellow) $SINGLE_LINE_SEPARATOR |@")
            println ansi.string("@|bold,fg(yellow) Directory: ${directory.getName()} |@")
            println ansi.string("@|bold,fg(yellow) $SINGLE_LINE_SEPARATOR |@")

            directory.getTags().each { Tag tag ->
                println ansi.string("@|italic,fg(yellow) " +
                        "${counter++}. ${tag.getTagName()} | ${tag.getTagType()} | ${tag.getDescription()} |@")
            }
            counter = 0
        }
    }

    private List<File> scan() {
        def videoFileSearchTask = new VideoFileSearchTask(inputFolder as File)
        return videoFileSearchTask.findVideoFiles()
    }
}
