package decanat.grails
/**
 * decanat.grails.User domain class.
 */
class User {
    static transients = ['pass']
    static hasMany = [authorities: Role]
    static belongsTo = Role

    /** Username     */
    String username
    /** decanat.grails.User Real Name    */
    String userRealName
    /** MD5 Password     */
    String passwd
    /** enabled     */
    boolean enabled

    String email
    boolean emailShow

    /** description     */
    String description = ''

    /** plain password to create a MD5 password     */
    String pass = '[secret]'

    static constraints = {
        username(blank: false, unique: true)
        userRealName(blank: false)
        passwd(blank: false, minSize: 4)
        email(email: true, blank: false)
//        confirmPasswd(confirm:true)
        enabled()
    }

    static mapping = {
        table 'users'
    }
}
