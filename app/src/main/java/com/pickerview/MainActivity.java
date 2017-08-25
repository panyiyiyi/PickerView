package com.pickerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(getDatas());
            }
        });
    }

    private List<String> getDatas() {
        List<String> datas = new ArrayList<>();
        datas.add("测试一");
        datas.add("测试二");
        datas.add("测试三");
        datas.add("测试四");
        datas.add("测试五");
        datas.add("测试六");
        return datas;
    }

    private void showPopupWindow(List<String> datas) {
        View inflate = getLayoutInflater().inflate(R.layout.popupwindow, null);
        TextView cancle = (TextView) inflate.findViewById(R.id.cancle);
        TextView comfirm = (TextView) inflate.findViewById(R.id.comfirm);
        PickerView pickerView = (PickerView) inflate.findViewById(R.id.pickerView);
        pickerView.setData(datas);
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);

        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                s = text;
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });

        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(s);
                popupWindow.dismiss();
            }
        });


    }
}
