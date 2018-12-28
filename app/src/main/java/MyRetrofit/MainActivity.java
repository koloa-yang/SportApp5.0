//package MyRetrofit;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.example.lenovo.app.R;
//
//import java.io.IOException;
//
//import MyRetrofit.Service;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivity extends AppCompatActivity{
//
//    //创建判定标准值judge_login
//    int judge_login=0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
//        //创建retrofit实例
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("http://172.20.10.8:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        //创建API接口实例
//        Service service=retrofit.create(Service.class);
//
//        //封装网络请求
////        Call<ResponseBody> call=service.getString(1220562);
//        Call<ResponseBody> call2=service.getLogin("16301009","235070");
////        Call<ResponseBody> call2=service.getLogin();
//
////        call.enqueue(new Callback<ResponseBody>() {
////            @Override
////            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                /**处理返回的数据结果*/
////                try {
////                    String s=response.body().string();
////                    Log.e("-------",s);
////                }
////                catch(IOException e){
////                    e.printStackTrace();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<ResponseBody> call, Throwable t) {
////                Log.e("-------",t.toString());
////            }
////        });
//
//        call2.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String s=response.body().string();
//                    if(s.equals("true")){
//                        judge_login=1;
//                    }
//                    Log.e("1231231321231",s);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("-------",t.toString());
//            }
//        });
//    }
//
//
//}