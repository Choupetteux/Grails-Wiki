package wikisimple

class WikiController {

    def index() {
        [articles:Article.list(), categories:Categorie.list()]
    }

    def listeArticle(Categorie categorie){
        render(view: "index.gsp", model: [
                articles:Article.findAllByCategorie(categorie),
                categories:Categorie.list()
                ]
        )
    }

    def article(Article article, int ctgId){
        render(view: "index.gsp", model: [
                categories:Categorie.list(),
                articleSelected: Article.find(article),
                articles:Article.findAllByCategorie(Categorie.find {id == ctgId})
                ]
        )
    }

    def listHistory(Article article){
        render(view: "history.gsp", model: [
                categories:Categorie.list(),
                revisions:Revision.findAllByArticle(article),
                artId:article.id
        ]
        )
    }

    def history(Revision revision, int artId){
        render(view: "history.gsp", model: [
                categories:Categorie.list(),
                revisionSelected: Revision.find(revision),
                revisions:Revision.findAllByArticle(Article.find {id == artId}),
                artId:artId,
                ctgId:Article.find {id == artId}?.categorie.id
        ]
        )
    }

    def createArticle(){
        [categories:Categorie.list()]
    }

    def createCategorie(){
        Categorie categorie = new Categorie(name: params.name).save()
        forward(controller:"wiki", action:"index")
    }

    def editerArticle(Article article) {
        render(view: "editArticle.gsp", model: [
                categories     : Categorie.list(),
                articleSelected: Article.find(article),
        ]
        )
    }

    def saveEdit(){
        Article article = Article.get(params.article)
        Revision revision = new Revision(article: article, oldTitle: article.title, oldCategorie: article?.categorie, oldContent: article.content).save(failOnError: true)
        article.title = params.title
        article.categorie = Categorie.find {id == params.categorie}
        Content content = new Content(content: params.content).save(failOnError: true)
        article.content = content
        if(article.validate()){
            article.save(flush: true)
        }
        else{
            render("Une erreur est survenu")
        }
        forward(controller:'wiki', action:"index")
    }

    def save(){
        Content content = new Content(content: params.content).save()
        Article article = new Article(title: params.title, categorie: params.categorie, content:content).save()
        forward(controller:"wiki", action:"index")
    }

    def deleteArticle(Article article){
        article.delete(flush: true, failOnError: true)
        forward(action:"index")
    }




}
