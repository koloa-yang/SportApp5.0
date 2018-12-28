package Mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.app.MainActivity;
import com.example.lenovo.app.R;
import com.tencent.tauth.Tencent;

import static android.app.Activity.RESULT_OK;
import static android.provider.UserDictionary.Words.APP_ID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private Dialog progressDialog;
    private Button loginBtn;
    private TextView toQQ;
    private TextView tosignin;
    private Tencent mTencent;
    private QQCallback qqCallback;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        Tencent.onActivityResultData(requestCode,resultCode,data,qqCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //QQ登录
        mTencent = Tencent.createInstance(APP_ID, getActivity().getApplicationContext());
        qqCallback = new QQCallback();
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn=view.findViewById(R.id.login);
        loginBtnListener();
        tosignin=view.findViewById(R.id.toSignin);
        toQQ=view.findViewById(R.id.toQQ);//声明Tencent
        setToRegister();
        setToQQ();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void loginBtnListener() {
        // TODO Auto-generated method stub
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog = new Dialog(getActivity(), R.style.progress_dialog);
                progressDialog.setContentView(R.layout.activity_waiting);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
                msg.setText("卖力加载中");
                progressDialog.show();

                Log.i("定义构造方法", "点击事件");
                //登录
                //如果登陆成功 进入我的主页

                progressDialog.hide();
                ((MainActivity)getActivity()).replaceFragment("mine");
            }
        });


    }
    private void setToRegister(){
        tosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (getActivity()!=null){
                ((MainActivity)getActivity()).replaceFragment("register");
                }
            }
        });
    }

    private void setToQQ(){
        toQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent.login(getActivity(),"all",qqCallback);
            }
        });
    }

}
