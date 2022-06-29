package ramizbek.aliyev.yakuniy.db

interface MyDBService {
    fun createUser(user: User)
    fun readUser(): ArrayList<User>

}