package enlist.grails

class CatalogItemCategory {

    String category

    static constraints = {
        category(blank: false)
    }

    String toString() {
        return "${category}"
    }
}
