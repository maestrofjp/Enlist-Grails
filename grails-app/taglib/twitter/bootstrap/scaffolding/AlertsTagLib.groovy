package twitter.bootstrap.scaffolding

class AlertsTagLib {

	static namespace = "bootstrap"

	def alert = { attrs, body ->
		out << '<div class="alert alert-error ' << attrs.class.tokenize().join(" ") << '">'
		out << '<a class="close" data-dismiss="alert">&times;</a>'
		out << body()
		out << '</div>'
	}

}
