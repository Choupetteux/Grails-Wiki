package wikisimple

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RevisionServiceSpec extends Specification {

    RevisionService revisionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Revision(...).save(flush: true, failOnError: true)
        //new Revision(...).save(flush: true, failOnError: true)
        //Revision revision = new Revision(...).save(flush: true, failOnError: true)
        //new Revision(...).save(flush: true, failOnError: true)
        //new Revision(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //revision.id
    }

    void "test get"() {
        setupData()

        expect:
        revisionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Revision> revisionList = revisionService.list(max: 2, offset: 2)

        then:
        revisionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        revisionService.count() == 5
    }

    void "test delete"() {
        Long revisionId = setupData()

        expect:
        revisionService.count() == 5

        when:
        revisionService.delete(revisionId)
        sessionFactory.currentSession.flush()

        then:
        revisionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Revision revision = new Revision()
        revisionService.save(revision)

        then:
        revision.id != null
    }
}
