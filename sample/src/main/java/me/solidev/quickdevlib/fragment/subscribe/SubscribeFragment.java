package me.solidev.quickdevlib.fragment.subscribe;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.module.list.BaseFragment;
import me.solidev.library.ui.widget.subscribeview.AbsSubscribePopWindow;
import me.solidev.quickdevlib.R;

/**
 * Created by _SOLID
 * Date:2016/10/17
 * Time:17:23
 * Desc:
 */

public class SubscribeFragment extends BaseFragment {

    private AbsSubscribePopWindow<MyChannel1> pop;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_subscribe;
    }

    @Override
    protected void setUpView() {
        final Button btn = $(R.id.btn_subscribe);
        pop = new MySubscribePopWindow(getContext());
        pop.setNewData(getData());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.showAsDropDown(btn);
            }
        });
    }

    @Override
    protected void setUpData() {

    }

    public List<MyChannel1> getData() {
        List<MyChannel1> channels = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            MyChannel1 channel = new MyChannel1();
            channel.setId("id" + i);
            channel.setTitle("频道b" + i);
            if (i == 0) {
                channel.setIsFix(1);
            }
            if (i < 8) {
                channel.setIsSubscribe(1);
            } else {
                channel.setIsSubscribe(0);
            }
            channels.add(channel);
        }

        return channels;

    }


}
