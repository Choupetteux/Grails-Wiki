package wikisimple

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ContentController {

    ContentService contentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond contentService.list(params), model:[contentCount: contentService.count()]
    }

    def show(Long id) {
        respond contentService.get(id)
    }

    def create() {
        respond new Content(params)
    }

    def save(Content content) {
        if (content == null) {
            notFound()
            return
        }

        try {
            contentService.save(content)
        } catch (ValidationException e) {
            respond content.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'content.label', default: 'Content'), content.id])
                redirect content
            }
            '*' { respond content, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond contentService.get(id)
    }

    def update(Content content) {
        if (content == null) {
            notFound()
            return
        }

        try {
            contentService.save(content)
        } catch (ValidationException e) {
            respond content.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'content.label', default: 'Content'), content.id])
                redirect content
            }
            '*'{ respond content, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        contentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'content.label', default: 'Content'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
