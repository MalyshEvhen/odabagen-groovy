package com.github.malyshevhen.util.screenshot

import com.github.malyshevhen.model.Picture
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor

class ScreenshotUtil {
    private ScreenshotUtil() {
    }

    static Picture takeScreenshots(File file) {
        def time = '00:00:04' // In format: HH:mm:ss
        // "ffmpeg -ss 00:00:04 -i input.mp4 -frames:v 1 screenshot.png"

        def filePath = file.absolutePath
        def screenshotPath = filePath
                .replace('.mov', '.jpeg')
                .replace('.mp4', '.jpeg')

        def screenshotName = file.getName()
                .replace('.mov', '.jpeg')
                .replace('.mp4', '.jpeg')

        def screenshot

        def command = "ffmpeg -ss ${time} -i ${filePath} -frames:v 1 ${screenshotPath}"

        println "FFMPEG COMMAND: => ${command}"

        def cmdLine = CommandLine.parse(command)
        def executor = new DefaultExecutor()
        executor.setExitValue(0) // Specify the expected exit code

        try {
            int exitValue = executor.execute(cmdLine)
            if (exitValue == 0) {
                screenshot =
                        new Picture(
                                name: screenshotName,
                                path: screenshotPath,
                                file: file)
                println "Command executed successfully."
                return screenshot
            } else {
                println "Command failed with exit code: ${exitValue}"
                throw new RuntimeException()
            }
        } catch (Exception e) {
            println "Error executing command: ${e.getMessage()}"
            throw new RuntimeException()
        }

    }
}
