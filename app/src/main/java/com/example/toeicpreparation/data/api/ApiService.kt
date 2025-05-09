import com.example.toeicpreparation.data.remote.LoginRequest
import com.example.toeicpreparation.data.remote.LoginResponse
import com.example.toeicpreparation.data.remote.Vocabulary
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import com.example.toeicpreparation.data.remote.VocabularyTopic
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("admin/topic/findAll")
    fun findAllTopic(): Call<List<VocabularyTopic>>

    @GET("admin/sub-topic/findAllByTopic/{id}")
    fun findAllSubTopicByTopic(@Path("id") id: Int): Call<List<VocabularySubTopic>>

    @GET("admin/sub-topic/findAllByTopic/{id}")
    fun findAllVocabularyBySubTopic(@Path("id") id: Int): Call<List<Vocabulary>>
}
