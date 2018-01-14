package com.junshan.pub.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.junshan.pub.R;
import com.junshan.pub.databinding.FgWheelDialogBinding;
import com.junshan.pub.widget.CommonDialog;
import com.junshan.pub.widget.WheelView;

import java.util.List;

/**
 * 底部弹起选择框
 * Created by chenjunshan on 11/28/17.
 */

public abstract class BottomSelectDialog {
    private CommonDialog dialog;
    private FgWheelDialogBinding binding;
    private Context context;

    public BottomSelectDialog(Context context) {
        this.context = context;
    }

    public void show(final List<String> datas, int position) {
        if (dialog == null) {
            dialog = new CommonDialog.Builder(context)
                    .setResId(R.layout.fg_wheel_dialog)
                    .setWidth(1f)
                    .setGravity(Gravity.BOTTOM)
                    .build();
            binding = (FgWheelDialogBinding) dialog.getBinding();
            binding.wheelView.setOffset(1);
            binding.wheelView.setItems(datas);
            binding.wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {

                }
            });
            binding.tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int position = binding.wheelView.getSeletedIndex();
                        onSuccess(position, datas.get(position));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
        }
        binding.wheelView.setSeletion(position);
        dialog.show();
    }

    protected abstract void onSuccess(int position, String content);
}
