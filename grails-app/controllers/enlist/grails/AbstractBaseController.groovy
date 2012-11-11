package enlist.grails

abstract class AbstractBaseController {
    def springSecurityService
    protected def getLoginUser() {
        if (!springSecurityService.isLoggedIn()) return null
        User.findByUsername(springSecurityService.authentication.name)
    }
    /**
     * not necessary to be admin
     * @return
     */
    protected boolean hasControllerWriteAccess() {
        if (loginUser)
            for(Role role : loginUser.authorities)
                if(role.authority in rolesWithWriteAccess) return true
        return false
    }
    /**
     * define roles that can perform admin tasks. such as delete, edit, create.
     * @return
     */
     protected abstract def getRolesWithWriteAccess();
}
