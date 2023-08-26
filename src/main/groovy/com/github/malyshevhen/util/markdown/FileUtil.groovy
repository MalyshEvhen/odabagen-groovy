package com.github.malyshevhen.util.markdown

import com.github.malyshevhen.model.Markdown

class FileUtil {

    static void save(Markdown markdown) {
        def path = markdown.path
        def markdownFile = new File(path)

        markdownFile << markdown.content
        println('Markdown content appended to the file successfully.')
    }

}
