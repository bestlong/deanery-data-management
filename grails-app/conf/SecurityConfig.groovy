security {

    // see DefaultSecurityConfig.groovy for all settable/overridable properties

    //TODO Реализовать разбивку по ролям

    active = true

    loginUserDomainClass = "decanat.grails.User"
    authorityDomainClass = "decanat.grails.Role"
//	requestMapClass = "decanat.grails.Requestmap"

    useRequestMapDomainClass = false

    requestMapString = '''
        CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
        PATTERN_TYPE_APACHE_ANT

        /=IS_AUTHENTICATED_ANONYMOUSLY
        /login/**=IS_AUTHENTICATED_ANONYMOUSLY
        /logout/**=IS_AUTHENTICATED_ANONYMOUSLY
        /js/**=IS_AUTHENTICATED_ANONYMOUSLY
        /css/**=IS_AUTHENTICATED_ANONYMOUSLY
        /images/**=IS_AUTHENTICATED_ANONYMOUSLY
        /plugins/**=IS_AUTHENTICATED_ANONYMOUSLY
        /index/**=IS_AUTHENTICATED_FULLY,IS_AUTHENTICATED_REMEMBERED
        /profile/**=IS_AUTHENTICATED_FULLY,IS_AUTHENTICATED_REMEMBERED
        /printer/**=IS_AUTHENTICATED_FULLY,IS_AUTHENTICATED_REMEMBERED
        /**=ROLE_ADMIN,IS_AUTHENTICATED_REMEMBERED
    '''


}
