package example


import grails.rest.*

//import grails.converimport
import groovy.transform.CompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

@CompileStatic
@Secured('isAuthenticated()')
class HomeController {
    static responseFormats = ['json', 'xml']
    SpringSecurityService springSecurityService

    def index() {
        [name: loggedUsername()]
    }

    String loggedUsername() {
        if (springSecurityService.principal == null) {
            return null
        }
        if (springSecurityService.principal instanceof String) {
            return springSecurityService.principal
        }
        if (springSecurityService.principal instanceof GrailsUser) {
            return ((GrailsUser) springSecurityService.principal).username
        }
        null
    }

}

