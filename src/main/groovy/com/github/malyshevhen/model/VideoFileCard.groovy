package com.github.malyshevhen.model

import com.drew.metadata.Metadata

class VideoFileCard {
    @Delegate File file
    @Delegate Markdown markdown
    Metadata metadata
    def picture = new HashSet<Picture>()

    VideoFileCard(Map properties) {
        this.file = properties.file as File
        this.markdown = properties.markdown as Markdown
        this.metadata = properties.metadata as Metadata
        this.picture.add(properties.addPicture as Picture)
    }
}
