package wikisimple

class Categorie {

    int id
    String name

    static constraints = {

    }

    static hasMany = [articles: Article]

    @Override
    String toString() {
        return name
    }
}
