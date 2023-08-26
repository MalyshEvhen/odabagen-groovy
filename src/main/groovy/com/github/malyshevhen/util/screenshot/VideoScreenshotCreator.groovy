package com.github.malyshevhen.util.screenshot

import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor

class VideoScreenshotCreator {
    private VideoScreenshotCreator() {
    }

    static void takeScreenshots(File file) {
        def time = '00:00:04' // In format: HH:mm:ss
        // "ffmpeg -ss 00:00:04 -i input.mp4 -frames:v 1 screenshot.png"

        def filePath = file.absolutePath
        def screenshotPath = filePath.replace('.mov', '.jpeg').replace('.mp4', '.jpeg')

        def command = "ffmpeg -ss ${time} -i ${filePath} -frames:v 1 ${screenshotPath}"

        println "FFMPEG COMMAND: => ${command}"

        def cmdLine = CommandLine.parse(command)
        def executor = new DefaultExecutor()
        executor.setExitValue(0) // Specify the expected exit code

        try {
            int exitValue = executor.execute(cmdLine)
            if (exitValue == 0) {
                println "Command executed successfully."
            } else {
                println "Command failed with exit code: ${exitValue}"
            }
        } catch (Exception e) {
            println "Error executing command: ${e.getMessage()}"
        }

    }
}
