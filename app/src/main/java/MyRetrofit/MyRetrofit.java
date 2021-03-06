package MyRetrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public MyRetrofit(){
        //创建retrofit实例
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://172.30.83.75:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建API接口实例
        Service service=retrofit.create(Service.class);

        //封装网络请求
        Call<ResponseBody> call=service.getUnlock(1220562);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                /**处理返回的数据结果*/
                try {
                    String s=response.body().string();
                    Log.e("-------",s);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("-------",t.toString());
            }
        });
    }
}
