import com.example.toeicpreparation.data.remote.LoginRequest
import com.example.toeicpreparation.data.remote.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}
