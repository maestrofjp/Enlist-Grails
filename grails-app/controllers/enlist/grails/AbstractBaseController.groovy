package enlist.grails

abstract class AbstractBaseController {
    def springSecurityService
    protected def getLoginUser() {
        if (!springSecurityService.isLoggedIn()) return null
        User.findByUsername(springSecurityService.authentication.name)
    }
    protected boolean hasAdminAccess() {
        if (loginUser)
            for(Role role : loginUser.authorities)
                if(role.authority in adminRoles) return true
        return false
    }
    /**
     * define roles that can perform admin tasks. such as delete, edit, create.
     * @return
     */
     protected abstract def getAdminRoles();
}
