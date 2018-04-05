package wikisimple

class Image {

    int id
    int name

    static constraints = {
    }

    static belongsTo = [article: Article]
}
