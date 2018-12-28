package Course;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.lenovo.app.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TableLayout tableLayout;
    private List<CourseItem> courseItems;
    private List<String> courseName;
    private List<String> weekday;
    private Spinner courseNameSpinner;
    private Spinner weekdaySpinner;
    private ArrayAdapter courseNameSpinnerAdapter;
    private ArrayAdapter weekdaySpinnerAdapter;
    private boolean isCourseNameSpinnerFirst = true ;
    private boolean isWeekdaySpinnerFirst=true;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        tableLayout = view.findViewById(R.id.course_table);
        courseNameSpinner = (Spinner) view.findViewById(R.id.CourseName_spinner);
        weekdaySpinner = (Spinner) view.findViewById(R.id.Weekday_spinner);
        //初始化课程表
        onCreateTable();
        setCourseNameSpinnerAdapter();
        setWeekdaySpinnerAdapter();
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
    private List<CourseItem> initCourseItems(){
        courseItems = new ArrayList<CourseItem>();
        courseName = new ArrayList<String>();
        weekday= new ArrayList<String>();
        CourseItem course= new CourseItem("动感单车","星期一","17:00-18:00","岳云鹏");
        courseItems.add(course);
        courseName.add(course.getCname());weekday.add(course.getCdate());
        course = new CourseItem("普拉提","星期二","17:00-18:00","岳云鹏");
        courseItems.add(course);
        courseName.add(course.getCname());weekday.add(course.getCdate());
        course = new CourseItem("高温瑜伽","星期三","17:00-18:00","张杰");
        courseItems.add(course);
        courseName.add(course.getCname());weekday.add(course.getCdate());
        course = new CourseItem("仰卧起","星期四","17:00-18:00","张杰");
        courseItems.add(course);
        courseName.add(course.getCname());weekday.add(course.getCdate());
        return courseItems;
    }
    private void onCreateTableTitle(){
        tableLayout.removeAllViews();
        TableRow row = new TableRow(this.getActivity());
        //创建显示的内容,这里创建的是一列
        TextView cname = new TextView(this.getActivity());
        cname.setGravity(Gravity.CENTER);
        TextView weekday = new TextView(this.getActivity());
        weekday.setGravity(Gravity.CENTER);
        TextView time = new TextView(this.getActivity());
        time.setGravity(Gravity.CENTER);
        TextView coach = new TextView(this.getActivity());
        coach.setGravity(Gravity.CENTER);
        //设置显示内容
        cname.setText("课程");
        weekday.setText("星期");
        time.setText("时间");
        coach.setText("教练");
        //添加到Row
        row.addView(cname,0);
        row.addView(weekday,1);
        row.addView(time,2);
        row.addView(coach,3);
        //将一行数据添加到表格中
        tableLayout.addView(row);
    }
    private void onCreateTable(){
        onCreateTableTitle();
        //初始化课程数据list 并新建迭代器
        Iterator iter = initCourseItems().iterator();
        while(iter.hasNext()) {
            CourseItem i=(CourseItem)iter.next();
            //创建一行
            TableRow row = new TableRow(this.getActivity());

            //创建显示的内容,这里创建的是一列
            TextView cname = new TextView(this.getActivity());
            cname.setGravity(Gravity.CENTER);
            TextView weekday = new TextView(this.getActivity());
            weekday.setGravity(Gravity.CENTER);
            TextView time = new TextView(this.getActivity());
            time.setGravity(Gravity.CENTER);
            TextView coach = new TextView(this.getActivity());
            coach.setGravity(Gravity.CENTER);

            //设置显示内容
            cname.setText(i.getCname());
            weekday.setText(i.getCdate());
            time.setText(i.getCtime());
            coach.setText(i.getCcoach());
            //添加到Row
            row.addView(cname,0);
            row.addView(weekday,1);
            row.addView(time,2);
            row.addView(coach,3);

            //将一行数据添加到表格中
            tableLayout.addView(row);
        }
    }
    private void  updateTable(String data){
        onCreateTableTitle();
        //初始化课程数据list 并新建迭代器
        Iterator iter = initCourseItems().iterator();
        while(iter.hasNext()) {
            CourseItem i=(CourseItem)iter.next();
            if(i.getCname().equals(data)||i.getCdate().equals(data)){
                TableRow row = new TableRow(this.getActivity());//创建一行
                //创建显示的内容,这里创建的是一列
                TextView cname = new TextView(this.getActivity());
                cname.setGravity(Gravity.CENTER);
                TextView weekday = new TextView(this.getActivity());
                weekday.setGravity(Gravity.CENTER);
                TextView time = new TextView(this.getActivity());
                time.setGravity(Gravity.CENTER);
                TextView coach = new TextView(this.getActivity());
                coach.setGravity(Gravity.CENTER);
                //设置显示内容
                cname.setText(i.getCname());
                weekday.setText(i.getCdate());
                time.setText(i.getCtime());
                coach.setText(i.getCcoach());
                //添加到Row
                row.addView(cname,0);
                row.addView(weekday,1);
                row.addView(time,2);
                row.addView(coach,3);
                //将一行数据添加到表格中
                tableLayout.addView(row);
            }
        }
    }
    private void setCourseNameSpinnerAdapter(){
        courseNameSpinnerAdapter= new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, courseName);
        courseNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseNameSpinner.setAdapter(courseNameSpinnerAdapter);
        //spinner 点击事件
        courseNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isCourseNameSpinnerFirst) {
                    //第一次初始化spinner时，不显示默认被选择的第一项即可
                    onCreateTable();
                    isCourseNameSpinnerFirst = false ;
                    return;
                }
                String data = (String)courseNameSpinner.getItemAtPosition(position);//从spinner中获取被选择的数据
                updateTable(data);
                Log.i("spinner","position    "+position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void setWeekdaySpinnerAdapter(){
        weekdaySpinnerAdapter= new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, weekday);
        weekdaySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekdaySpinner.setAdapter(weekdaySpinnerAdapter);
        //spinner 点击事件
        weekdaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isWeekdaySpinnerFirst) {
                    //第一次初始化spinner时，不显示默认被选择的第一项即可
                    onCreateTable();
                    isWeekdaySpinnerFirst = false ;
                    return;
                }
                String data = (String)weekdaySpinner.getItemAtPosition(position);//从spinner中获取被选择的数据
                updateTable(data);
                Log.i("spinner","position    "+position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}
