package com.zsw.demoapplication.base;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import cn.trinea.android.common.base.CommonFragment;
import cn.trinea.android.common.util.PreferenceHelper;
/**
 * 基础Fragment
 * Created by liuyi on 15/10/15.
 */
public abstract class BaseFragment extends CommonFragment {

    private PreferenceHelper preferenceHelper;

    @Override
    public void preInit(Bundle savedInstanceState) {
        super.preInit(savedInstanceState);
    }

    public PreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }

    public boolean noNetWork() {
        return !hasNetWork();
    }

    public void showNetWorkError() {
        showToast("请检查您的网络!");
    }

    /**
     * 获取手机唯一标识
     * @return
     */
    public String getIMEI() {
        TelephonyManager TelephonyMgr = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }
}
