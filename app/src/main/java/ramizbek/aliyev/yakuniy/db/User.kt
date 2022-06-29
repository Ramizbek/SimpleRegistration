package ramizbek.aliyev.yakuniy.db

class User {
    var id:Int? = null
    var userName: String? = null
    var password: String? = null
    var image:String? = null

    constructor(userName: String?, password: String?, image: String?) {
        this.userName = userName
        this.password = password
        this.image = image
    }

    constructor(id: Int?, userName: String?, password: String?, image: String?) {
        this.id = id
        this.userName = userName
        this.password = password
        this.image = image
    }


}