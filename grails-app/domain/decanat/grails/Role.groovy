package decanat.grails

/**
 * Authority domain class.
 */
class Role {

	static hasMany = [people: User]



	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
        people(nullable: true)
		authority(blank: false, unique: true)
		description(blank: true)
	}
}
