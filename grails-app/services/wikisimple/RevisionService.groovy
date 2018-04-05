package wikisimple

import grails.gorm.services.Service

@Service(Revision)
interface RevisionService {

    Revision get(Serializable id)

    List<Revision> list(Map args)

    Long count()

    void delete(Serializable id)

    Revision save(Revision revision)

}