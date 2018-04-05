<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Simple Wiki">
    <title>SimpleWiki</title>
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
    <link rel="stylesheet" href="${resource(dir: 'css', file:'createArticle.css')}">

</head>
<body>
<div id="layout" class="content pure-g">
    <div id="nav" class="pure-u">
        <a href="#" class="nav-menu-button">Menu</a>
        <div class="logo-wiki">
            <h1 class="title-wiki"> <a href="${createLink(action:"index", controller:"wiki")}">Wiki'O'max</a></h1>
            <hr>
        </div>

    <g:form action="save">
        <div class="nav-inner">
            <div class="pure-menu">
                <li class="pure-menu-heading">Créer un article</li>
                <g:actionSubmit action="save" value="Sauvegarder l'article" class="button-success pure-button"/>
                <ul class="pure-menu-list">
                </ul>
            </div>
        </div>
    </div>

    <div id="main" class="pure-u-24-24">
            <div class="email-content">
                <div class="email-content-header pure-g">
                    <div class="pure-u-1-2">
                        <label class="email-content-title">Titre : </label>
                        <g:textField name="title"/>
                    </div>
                    <div class="pure-u-1-2">
                        <label class="email-content-title">Catégorie : </label>
                        <g:select name="categorie" from="${categories}" optionKey="id"/>
                    </div>
                </div>

                <div class="email-content-body">
                    <g:textArea name="content" placeholder="Ecrivez le contenu de votre article ici"/>
                </div>
            </div>
        </g:form>
    </div>
</div>


</body>
</html>
