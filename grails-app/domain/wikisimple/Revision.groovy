package wikisimple

import grails.core.GrailsApplication

class Revision {

    int id
    Date dateCreated
    Content oldContent
    String oldTitle
    Categorie oldCategorie

    static constraints = {
    }

    static belongsTo = [article: Article]


    @Override
    String toString() {
        return dateCreated
    }
}
