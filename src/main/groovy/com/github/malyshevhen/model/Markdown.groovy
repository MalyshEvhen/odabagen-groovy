package com.github.malyshevhen.model

class Markdown {
    @Delegate Picture picture
    String path
    String content

    Markdown(Map properties) {
        this.picture = properties.picture as Picture
        this.path = properties.path
        this.content = properties.content
    }
}
