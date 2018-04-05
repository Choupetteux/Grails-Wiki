package wikisimple

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RevisionController {

    RevisionService revisionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond revisionService.list(params), model:[revisionCount: revisionService.count()]
    }

    def show(Long id) {
        respond revisionService.get(id)
    }

    def create() {
        respond new Revision(params)
    }

    def save(Revision revision) {
        if (revision == null) {
            notFound()
            return
        }

        try {
            revisionService.save(revision)
        } catch (ValidationException e) {
            respond revision.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'revision.label', default: 'Revision'), revision.id])
                redirect revision
            }
            '*' { respond revision, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond revisionService.get(id)
    }

    def update(Revision revision) {
        if (revision == null) {
            notFound()
            return
        }

        try {
            revisionService.save(revision)
        } catch (ValidationException e) {
            respond revision.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'revision.label', default: 'Revision'), revision.id])
                redirect revision
            }
            '*'{ respond revision, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        revisionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'revision.label', default: 'Revision'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'revision.label', default: 'Revision'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
