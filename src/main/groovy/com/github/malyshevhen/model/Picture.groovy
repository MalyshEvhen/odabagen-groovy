package com.github.malyshevhen.model

class Picture {
    @Delegate File file
    String name
    String path

    Picture(Map properties) {
        this.name = properties.name
        this.path = properties.path
        this.file = properties.file as File
    }

    def getPictureName() {
        return this.name
    }
}
