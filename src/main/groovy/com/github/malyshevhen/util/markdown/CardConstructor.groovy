package com.github.malyshevhen.util.markdown


import com.github.malyshevhen.model.Markdown
import com.github.malyshevhen.model.Picture

import static com.github.malyshevhen.util.markdown.CardConstructor.CardType.*

class CardConstructor {

    Picture picture

    CardConstructor() {}

    CardConstructor(Picture picture) {
        this.picture = picture
    }

    def buildCard(CardType type) {
        def path = picture.getAbsolutePath()
                .replace('.mov', '.md')
                .replace('.mp4', '.md')

        return switch (type) {
            case ARCHIVE -> new Markdown(picture: picture, content: ARCHIVE_CARD_TEMPLATE, path: path)
            case MODERN -> new Markdown(picture: picture, content: MODERN_CARD_TEMPLATE, path: path)
        }
    }

    enum CardType {
        ARCHIVE,
        MODERN
    }

    public ARCHIVE_CARD_TEMPLATE = """
---
Type: archive
---


---
# 🗃️ ARCHIVE
---

## 💾 FILE:

[[${picture.getName()}]]

## 🖼️ STILLS:

> Change the name in square quotes, and put link in braces:
> \\\\!\\\\[ `name of the picture` \\\\]\\\\( `/your/picture/url/image.png` )

![vlcsnap-2023-08-13-20h02m03s451.png](${picture.getPictureName()})

## 📙 TRANSCRIPTION FILE

> Add link to the file in double square brackets:
> \\\\[\\\\[ `your_file_name_with.extension` \\\\]\\\\]

[[${picture.getPictureName()}]]

## 📝 DESCRIPTIONS

> _Put description to the card here_

## ✉️ COMMENTS

> _Put your comments here_\\n\\n
"""
    public MODERN_CARD_TEMPLATE = """
---
Type: archive
---


---
# 🗃️ ARCHIVE
---

## 💾 FILE:

[[${picture.getName()}]]

## 🖼️ STILLS:

> Change the name in square quotes, and put link in braces:
> \\\\!\\\\[ `name of the picture` \\\\]\\\\( `/your/picture/url/image.png` )

![vlcsnap-2023-08-13-20h02m03s451.png](${picture.getPictureName()})

## 📙 TRANSCRIPTION FILE

> Add link to the file in double square brackets:
> \\\\[\\\\[ `your_file_name_with.extension` \\\\]\\\\]

[[${picture.getPictureName()}]]

## 📝 DESCRIPTIONS

> _Put description to the card here_

## ✉️ COMMENTS

> _Put your comments here_\\n\\n
"""

}
