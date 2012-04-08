// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text/plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://app.stu.cn.ua/${appName}"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {

    appenders {
        rollingFile name: "infoAppender",
                maxFileSize: 10000,
                file: "logs/info.log",
                threshold: org.apache.log4j.Level.INFO,
                layout: pattern(conversionPattern: "%d{ISO8601} %p %c{10} %L %m%n")
        rollingFile name: "errorAppender",
                maxFileSize: 100000,
                file: "logs/error.log",
                threshold: org.apache.log4j.Level.ERROR,
                layout: pattern(conversionPattern: "%d{ISO8601} %p %c{10} %L %m%n")
        rollingFile name: "debugAppender",
                maxFileSize: 10000,
                file: "logs/debug.log",
                threshold: org.apache.log4j.Level.DEBUG,
                layout: pattern(conversionPattern: "%d{ISO8601} %p %c{10} %L %m%n")
    }



    info infoAppender: 'grails.app.bootstrap'

    debug errorAppender: ['grails.app.controller', 'grails.app.service']

    debug debugAppender: ['grails.app.controller', 'grails.app.service']

    error errorAppender: "StackTrace"

}

// set jquery as default javascript & ajax library
grails.views.javascript.library = "jquery"

//log4j.logger.org.springframework.security='off,stdout'

// Added by the JQuery Validation plugin:
jqueryValidation.packed = true
jqueryValidation.cdn = false  // false or "microsoft"
jqueryValidation.additionalMethods = false

// Added by the JQuery Validation UI plugin:
jqueryValidationUi {
    errorClass = 'error_field'
    validClass = 'valid'
    onsubmit = true
    renderErrorsOnTop = false

    qTip {
        packed = true
        classes = 'ui-tooltip-blue ui-tooltip-jtools ui-tooltip-rounded'
    }

    /*
       Grails constraints to JQuery Validation rules mapping for client side validation.
       Constraint not found in the ConstraintsMap will trigger remote AJAX validation.
     */
    StringConstraintsMap = [
            blank: 'required', // inverse: blank=false, required=true
            creditCard: 'creditcard',
            email: 'email',
            inList: 'inList',
            minSize: 'minlength',
            maxSize: 'maxlength',
            size: 'rangelength',
            matches: 'matches',
            notEqual: 'notEqual',
            url: 'url',
            nullable: 'required',
            unique: 'unique',
            validator: 'validator'
    ]

    // Long, Integer, Short, Float, Double, BigInteger, BigDecimal
    NumberConstraintsMap = [
            min: 'min',
            max: 'max',
            range: 'range',
            notEqual: 'notEqual',
            nullable: 'required',
            inList: 'inList',
            unique: 'unique',
            validator: 'validator'
    ]

    CollectionConstraintsMap = [
            minSize: 'minlength',
            maxSize: 'maxlength',
            size: 'rangelength',
            nullable: 'required',
            validator: 'validator'
    ]

    DateConstraintsMap = [
            min: 'minDate',
            max: 'maxDate',
            range: 'rangeDate',
            notEqual: 'notEqual',
            nullable: 'required',
            inList: 'inList',
            unique: 'unique',
            validator: 'validator'
    ]

    ObjectConstraintsMap = [
            nullable: 'required',
            validator: 'validator'
    ]

    CustomConstraintsMap = [
            phone: 'true', // International phone number validation
            phoneUS: 'true',
            alphanumeric: 'true',
            letterswithbasicpunc: 'true',
            lettersonly: 'true'
    ]

grails.gorm.failOnError= true
}


// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'decanat.grails.domain.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'decanat.grails.domain.UserRole'
grails.plugins.springsecurity.authority.className = 'decanat.grails.domain.Role'

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugins.springsecurity.interceptUrlMap = [
        '/':                            ['IS_AUTHENTICATED_FULLY'],
        '/login/**':                    ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/logout/**':                   ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/js/**':                       ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/css/**':                      ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/images/**':                   ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/plugins/**':                  ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/JQueryRemoteValidator/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/index/**':                    ['IS_AUTHENTICATED_FULLY'],
        '/profile/**':                  ['IS_AUTHENTICATED_FULLY'],
        '/printer/**':                  ['IS_AUTHENTICATED_FULLY'],
        '/DBFImport/**':                  ['ROLE_DEAN'],
        '/user/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/speciality/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/subject/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/chair/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/university/**':                  ['ROLE_DEAN'],
        '/deanery/**':                  ['ROLE_PROREKTOR'],
        '/planCreation/**':                  ['ROLE_DEAN'],
        '/planInit/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/addSubjects/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/practice/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/stateExam/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/CSVExport/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/semestr/**':                  ['ROLE_DEAN', 'ROLE_PROREKTOR'],
        '/**':                          ['IS_AUTHENTICATED_FULLY']
]