package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.soon.test.myapplication.R;
import com.soon.test.myapplication.utils.Utils;

import java.util.LinkedList;
import java.util.List;

public class PopupWindowActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PopupWindowActivity", "onCreate");
        setContentView(R.layout.popupwindow_activity);

        mEdit1 = (EditText) findViewById(R.id.edit1);
        mEdit1.clearFocus();
        mEdit1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus && TextUtils.isEmpty(mEdit1.getText())){
                    showPopWindow(mEdit1);
                }
            }
        });
    }

    private EditText mEdit1;
    int mRecommandNumPerPage = 5;
    int curRecommendIndex = 0;

    private View getCommandRecommendView(Context context){
        LinearLayout crParent = new LinearLayout(context);
        crParent.setOrientation(LinearLayout.VERTICAL);
        crParent.setBackgroundColor(Color.WHITE);
        return crParent;
    }

    private void showPopWindow(View archor){
        Rect rect = new Rect();
        archor.getGlobalVisibleRect(rect);



        int popHeight = 700;//pop高度

        if(mPopupWindow == null){
            mPopupWindow = new PopupWindow(getCommandRecommendView(archor.getContext()), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            mPopupWindow.setFocusable(false);
        }

        List<String> recommendStrs = new LinkedList<>();
        recommendStrs.add("一");
        recommendStrs.add("一而");
        recommendStrs.add("一一而");
        recommendStrs.add("一一一而");
        recommendStrs.add("一一一一而");
        recommendStrs.add("一一一一一而");
        recommendStrs.add("一一一一一一而");
        recommendStrs.add("一一一一一一一而");
        fillRecommandContent(recommendStrs);

        popHeight = getResources().getDisplayMetrics().heightPixels - rect.bottom;//pop高度
        mPopupWindow.setHeight(popHeight);
        mPopupWindow.showAtLocation(archor, Gravity.TOP | Gravity.LEFT,rect.left,rect.bottom);
//        popHeight = getResources().getDisplayMetrics().heightPixels - rect.bottom;//pop高度
//        mPopupWindow.setHeight(popHeight);
//            mCommandRecommendView.showAtLocation(koulingLayer,Gravity.TOP | Gravity.LEFT,rect.left,rect.bottom);
//        mPopupWindow.showAsDropDown(archor);
    }

    private void fillRecommandContent(final List<String> recommendStrs){
        if(mPopupWindow != null){
            View contentView = mPopupWindow.getContentView();
            if(contentView != null && contentView instanceof LinearLayout){
                final Context context = contentView.getContext();
                //先清空布局
                LinearLayout crParent = (LinearLayout)contentView;
                crParent.removeAllViews();

                //加上顶部固定布局
                RelativeLayout head = new RelativeLayout(context);
                LinearLayout.LayoutParams headLPs = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(context, 44));
                crParent.addView(head, headLPs);

                //头部标题
                TextView title = new TextView(context);
                title.setText("推荐口令");
                title.setTextColor(0xff777777);
                title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                RelativeLayout.LayoutParams titleLps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                titleLps.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
                titleLps.leftMargin = Utils.dip2px(context,12);
                head.addView(title, titleLps);

                TextView changeBtn = null;
                //换一批按钮
                if(recommendStrs.size() > mRecommandNumPerPage){
                    changeBtn = new TextView(context);
                    changeBtn.setText("换一批");
                    changeBtn.setTextColor(0xffE62555);
                    changeBtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                    RelativeLayout.LayoutParams changeBtnLps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    changeBtnLps.rightMargin = Utils.dip2px(context,12);
                    changeBtnLps.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                    changeBtnLps.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
                    head.addView(changeBtn, changeBtnLps);
                }

                curRecommendIndex = 0;
                List<String> firstPageStrs = getPageStrsFromIndex(recommendStrs);
                final View.OnClickListener itemListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v instanceof TextView){
                            String txt = ((TextView)v).getText().toString();
                            if( mEdit1 != null){
                                mEdit1.setText(txt);
                                mEdit1.setSelection(txt == null ? 0 :txt.length());
                            }
                        }
                    }
                };


                ScrollView crItemsScrollView = new ScrollView(context);
                LinearLayout.LayoutParams crItemsScrollViewLps = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                crParent.addView(crItemsScrollView,crItemsScrollViewLps);

                final int lineMaxlen = context.getResources().getDisplayMetrics().widthPixels;//一行最大的长度
                //所有提示语公用的布局，后续update时便于直接清空
                final LinearLayout crItemsParent = new LinearLayout(context);
                crItemsParent.setOrientation(LinearLayout.VERTICAL);
//                LinearLayout.LayoutParams crItemsParentLps = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                crParent.addView(crItemsParent,crItemsParentLps);
                crItemsScrollView.addView(crItemsParent);
                updatePage(context, crItemsParent, lineMaxlen, firstPageStrs, itemListener);

                if(changeBtn != null){
                    changeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updatePage(context, crItemsParent, lineMaxlen, getPageStrsFromIndex(recommendStrs), itemListener);
                        }
                    });
                }
            }
        }
    }

    private static final int MARGIN_RECOMMEND_ITEM_DP = 12;

    private void updatePage(Context context, LinearLayout crItemsParent,int lineMaxLen, List<String> strs, View.OnClickListener itemListener){
        crItemsParent.removeAllViews();

        int itemIndex = 0;
        do{
            LinearLayout recommendRow = new LinearLayout(context);
            recommendRow.setOrientation(LinearLayout.HORIZONTAL);
            recommendRow.setGravity(Gravity.CENTER_VERTICAL);
            int recommendRowPadding = Utils.dip2px(context, 6);
            recommendRow.setPadding(0,recommendRowPadding,0, recommendRowPadding);
            LinearLayout.LayoutParams recommendRowLps = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            crItemsParent.addView(recommendRow, recommendRowLps);

            int curItemsTotalLen = 0;
            String itemStr = strs.get(itemIndex);
            TextView recommendItem = getRecommendItem(context, itemStr, itemListener);
            do{
                curItemsTotalLen += recommendItem.getMeasuredWidth() + Utils.dip2px(context,MARGIN_RECOMMEND_ITEM_DP);
                recommendRow.addView(recommendItem);

                itemIndex++;
                if(itemIndex < strs.size()){
                    itemStr = strs.get(itemIndex);
                    recommendItem = getRecommendItem(context, itemStr, itemListener);
                }
            }while (itemIndex < strs.size() &&
                    (curItemsTotalLen + recommendItem.getMeasuredWidth() + 2*Utils.dip2px(context,MARGIN_RECOMMEND_ITEM_DP))<=lineMaxLen);
        }while(itemIndex < strs.size());

        curRecommendIndex += strs.size();
    }

    private TextView getRecommendItem(Context context, String itemStr, View.OnClickListener listener){
        TextView recommendItem = new TextView(context);
        recommendItem.setTextColor(Color.BLACK);
        recommendItem.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        recommendItem.setBackgroundColor(Color.RED);
        recommendItem.setGravity(Gravity.CENTER_VERTICAL);
        int recommendItemPadding = Utils.dip2px(context, 13);
        recommendItem.setPadding(recommendItemPadding,0,recommendItemPadding,0);
        recommendItem.setText(itemStr);
        recommendItem.setOnClickListener(listener);
        LinearLayout.LayoutParams recommendItemLps = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Utils.dip2px(context,30));
        recommendItemLps.leftMargin = Utils.dip2px(context, MARGIN_RECOMMEND_ITEM_DP);
        recommendItem.setLayoutParams(recommendItemLps);

        recommendItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);//measure一下便于外边获取长度

        return recommendItem;
    }

    private PopupWindow mPopupWindow = null;

    /** 根据索引获取到当前页的推荐口令内容*/
    private List<String> getPageStrsFromIndex(List<String> oriList){
        List<String> res = new LinkedList<String>();
        if(oriList != null){
            if(curRecommendIndex >= oriList.size()){
                curRecommendIndex = 0;
            }
            int toIndex = curRecommendIndex+mRecommandNumPerPage > oriList.size() ? oriList.size(): curRecommendIndex+mRecommandNumPerPage;
            res.addAll(oriList.subList(curRecommendIndex, toIndex));
        }
        return res;
    }
}
