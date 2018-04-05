package wikisimple

class Article {

    int id
    String title
    Content content
    Categorie categorie
    Date dateCreated

    static constraints = {
        categorie nullable: true
    }

    static hasMany = [revisions: Revision]


    @Override
    String toString() {
        return title
    }
}
