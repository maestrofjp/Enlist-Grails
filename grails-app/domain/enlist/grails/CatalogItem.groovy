package enlist.grails

class CatalogItem {

    String name
    String description
    Integer points
    CatalogItemCategory category
    byte[] photo
    Boolean available

    static constraints = {
        name(blank: false, maxSize: 500)
        description(blank: true, maxSize: 2000)
        points(blank: false)
        importFrom CatalogItemCategory
        available(default: true)
        photo(nullable: true)
    }
}
