package com.github.malyshevhen.util.markdown

import com.github.malyshevhen.model.Markdown
import com.github.malyshevhen.model.Picture

class MarkdownUtil {
    private final Picture picture

    MarkdownUtil(Picture picture) {
        this.picture = picture
    }

    /*    public ARCHIVE_CARD_TEMPLATE =
                  """
      ---
      Type: archive
      ---


      ---

      # 🗃️ ARCHIVE

      ---

      ## 💾 FILE:

      [[${picture.getAbsolutePath()}]]

      ## 🖼️ STILLS:

      > Change the name in square quotes, and put link in braces:
      > \\!\\[ `name of the picture` \\]\\( `/your/picture/url/image.png` \\)

      ![vlcsnap-2023-08-13-20h02m03s451.png](${picture.getPicturePath()})

      ## 📙 TRANSCRIPTION FILE

      > Add link to the file in double square brackets:
      > \\[\\[ `your_file_name_with.extension` \\]\\]

      [[${picture.name}]]

      ## 📝 DESCRIPTIONS

      > _Put description to the card here_

      ## ✉️ COMMENTS

      > _Put your comments here_

      """ */


    public ARCHIVE_CARD_TEMPLATE =
                    "---\n" +
                    "Type: archive\n" +
                    "---\n\n\n" +
                    "---\n\n" +
                    "# 🗃️ ARCHIVE\n\n" +
                    "---\n\n" +
                    "## 💾 FILE:\n\n" +
                    "[[${picture.getName()}]]\n\n" +
                    "## 🖼️ STILLS:\n\n" +
                    "> Change the name in square quotes, and put link in braces:\n" +
                    "> \\!\\[ `name of the picture` \\]\\( `/your/picture/url/image.png`)\n\n" +
                    "![vlcsnap-2023-08-13-20h02m03s451.png](${picture.getPictureName()})\n\n" +
                    "## 📙 TRANSCRIPTION FILE\n\n" +
                    "> Add link to the file in double square brackets:\n" +
                    "> \\[\\[ `your_file_name_with.extension` \\]\\]\n\n" +
                    "[[${picture.name}]]\n\n" +
                    "## 📝 DESCRIPTIONS\n\n" +
                    "> _Put description to the card here_\n\n" +
                    "## ✉️ COMMENTS\n\n" +
                    "> _Put your comments here_\n\n"

    def buildArchiveCard() {
        def path = picture.getAbsolutePath()
                .replace('.mov', '.md')
                .replace('.mp4', '.md')

        return new Markdown(picture: picture, content: ARCHIVE_CARD_TEMPLATE, path: path)
    }
}
