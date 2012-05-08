package decanat.grails.domain

import decanat.grails.Faculty

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
    Faculty faculty

    static hasMany = [userRoles: UserRole]

	static constraints = {
		username blank: false, unique: true
		password blank: false
        faculty nullable: true
	}

	static mapping = {
		password column: '`password`'
        table "users"
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
            encodePassword()
        }
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
