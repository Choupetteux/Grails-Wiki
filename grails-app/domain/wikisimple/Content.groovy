package wikisimple

import grails.config.Config

class Content {

    int id
    String content

    static constraints = {
        content type: 'text'
    }

    @Override
    String toString() {
        if(content != null){
            if(content.length() > 60){
                return content.substring(0, 60) + "..."
            }
            else{
                return content
            }


        }
    }
}
