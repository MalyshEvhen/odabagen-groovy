package com.github.malyshevhen.command.tasks

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

class VideoFileSearchTask extends RecursiveTask<List<File>> {
    private final File directory

    VideoFileSearchTask(File directory) {
        this.directory = directory
    }

    List<File> findVideoFiles() {
        ForkJoinPool pool = ForkJoinPool.commonPool()
        List<File> videoFiles = pool.invoke(new VideoFileSearchTask(directory))
        pool.shutdown()

        return videoFiles
    }

    @Override
    protected List<File> compute() {
        List<File> videoFiles = new ArrayList<>()

        if (directory.isDirectory()) {
            File[] files = directory.listFiles()

            if (files != null) {
                List<VideoFileSearchTask> subTasks = new ArrayList<>()

                for (File file : files) {
                    if (file.isDirectory()) {
                        /* Create a new subtask to search in this subdirectory */
                        VideoFileSearchTask subTask = new VideoFileSearchTask(file)
                        subTasks.add(subTask)
                        subTask.fork() /* Fork the subtask for parallel execution */
                    } else if (isVideoFile(file)) {
                        videoFiles.add(file)
                    }
                }

                /* Join results from subtasks */
                for (VideoFileSearchTask subTask : subTasks) {
                    videoFiles.addAll(subTask.join())
                }
            }
        }
        return videoFiles
    }

    private boolean isVideoFile(File file) {
        /* Get the file's extension */
        String fileName = file.getName()
        int lastDotIndex = fileName.lastIndexOf('.')
        if (lastDotIndex > 0) {
            String extension = fileName.substring(lastDotIndex + 1).toLowerCase()

            /* List of common video file extensions */
            String[] videoExtensions = ['mp4', 'avi', 'mkv', 'mov', 'wmv', 'flv', 'webm']

            /* Check if the extension matches a video extension */
            for (String videoExt : videoExtensions) {
                if (extension == videoExt) return true
            }
        }
        return false
    }
}
