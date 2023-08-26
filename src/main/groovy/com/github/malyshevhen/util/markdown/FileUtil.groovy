package com.github.malyshevhen.util.markdown

import com.github.malyshevhen.model.Markdown
import static picocli.CommandLine.Help.Ansi.AUTO as ansi

class FileUtil {

    static void save(Markdown markdown) {
        def path = markdown.path
        def markdownFile = new File(path)

        markdownFile << markdown.content
        println ansi.string('@|fg(green) Markdown content appended to the file successfully. |@')
    }

}
