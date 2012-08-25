package enlist.grails

class ErrorsController {

    def serverError() {
		// TODO: logging / email?
		
		render(view:'/errors/500')
	}
}
