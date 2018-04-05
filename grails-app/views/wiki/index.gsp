<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Simple Wiki">
    <title>SimpleWiki</title>
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
    <link rel="stylesheet" href="${resource(dir:'css', file:'animate.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file:'wiki.css')}">
    <script src="${resource(dir:'javascripts', file:'jquery-2.2.0.min.js')}"></script>
    <script src="${resource(dir: 'javascripts', file:'index.js')}"></script>

</head>
<body>
<div id="layout" class="content pure-g">
    <div id="nav" class="pure-u">
        <a href="#" class="nav-menu-button">Menu</a>
        <div class="logo-wiki">
            <h1 class="title-wiki">Wiki'O'max</h1>
        </div>
        <div class="nav-inner">
            <a class="button-success pure-button" href="${createLink(action:"index", controller:"wiki")}">Voir tout les articles</a>
            <a class="primary-button pure-button" href="${createLink(action:"createArticle", controller:"wiki")}">Ecrire un article</a>
            <hr>


        </div>


        <div class="nav-inner">
            <div class="pure-menu">
                <li class="pure-menu-heading">Catégorie</li>
                <ul class="pure-menu-list">
                    <g:each var="categorie" in="${categories}">
                        <li class="pure-menu-item"><a href="${createLink(action:'listeArticle', params:[id: "${categorie.id}", title: "${categorie.name}"])}" class="pure-menu-link">${categorie.name}</a></li>
                    </g:each>
                </ul>
            </div>
        </div>

        <br>
        <button id="btn-ctg" class="button-warning pure-button">Créer une catégorie</button>

        <g:form name="form-categorie" action="createCategorie" class="animated fadeIn" style="display:none;">
            <g:textField name="name" class="ctg-form" placeholder="Nom de la catégorie"/>
            <g:actionSubmit action="createCategorie" value="Créer la catégorie" class="pure-button" style="padding:0.5em;" />
        </g:form>
        <hr>
        <sec:ifLoggedIn>
            <label class="form-label">Bienvenue ${SecurityContextHolder.getContext().getAuthentication().getName()}</label>
            <g:link controller="logout" class="button-secondary pure-button">Déconnexion</g:link>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <button id="btn-login" class="button-secondary pure-button">Connexion</button>
            <form  action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform animated fadeInUp" style="display:none;" autocomplete="off">
                <label class="form-label">Pseudo :</label><br>
                <input type="text" class="text_ ctg-form" name="${usernameParameter ?: 'username'}" id="username"/>
                <label class="form-label">Mot de passe :</label><br>
                <input type="password" class="text_ ctg-form" name="${passwordParameter ?: 'password'}" id="password"/>
                <input type="submit" id="submit" value="${message(code: 'springSecurity.login.button')}"/>
            </form>
        </sec:ifNotLoggedIn>
    </div>

    <div id="list" class="pure-u-4-24">

    <g:if test="${articles?.size() == 0}">
        <div class="email-item pure-g">
            <div class="pure-u-3-4">
                <h4 class="email-subject">Aucun de nos articles appartient à cette catégorie</h4>
            </div>
        </div>
    </g:if>
    <g:else>
        <g:each var="article" in="${articles}">
            <div class="email-item pure-g">
                <div class="pure-u-3-4">
                    <h5 class="email-name">${article.categorie}</h5>
                    <h4 class="email-subject"><a href="${createLink(action:'article', params:[id:"${article.id}", ctgId:"${article.categorie.id}" ])}" >${article.title}</a></h4>
                    <p class="email-desc">
                        ${article.content}
                    </p>
                </div>
            </div>

        </g:each>
    </g:else>


    </div>

    <div id="main" class="pure-u-24-24">

        <div class="email-content">
            <div class="email-content-header pure-g">
                <div class="pure-u-1-2">
                    <h1 class="email-content-title">${articleSelected?.title}</h1>
                </div>

                <g:if test="${articleSelected != null}">
                    <div class="email-content-controls pure-u-1-2">
                        <a href="${createLink(action:'editerArticle', params:[id:"${articleSelected?.id}"])}" class="secondary-button pure-button">Editer</a>
                        <a href="${createLink(action:'deleteArticle', params:[id:"${articleSelected?.id}"])}" class="secondary-button pure-button">Supprimer</a>
                        <a href="${createLink(action:'listHistory', params:[id:"${articleSelected?.id}"])}" class="secondary-button pure-button">Historique des révisions</a>
                    </div>
                </g:if>
            </div>

            <div class="email-content-body">
                <g:each in="${articleSelected?.content?.content?.split("\\r?\\n")}">
                    <p>${it}</p>
                </g:each>
            </div>
        </div>
    </div>
</div>


</body>
</html>
