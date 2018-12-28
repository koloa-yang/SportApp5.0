package MyRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {
    @GET("number")//网址下面的子目录   category表示分类，因为子目录只有一点不一样
    Call<ResponseBody> getUnlock(@Query("id") int id);

    @POST("login")
    Call<ResponseBody> getLogin(@Query("userName") String username, @Query("passWord") String password);

    @POST("register")
    Call<ResponseBody> getRegister(@Query("userName") String username, @Query("passWord") String password);
}