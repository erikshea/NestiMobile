import com.nesti.myapplication.data.model.User
import com.nesti.myapplication.utils.ApiHelper
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}