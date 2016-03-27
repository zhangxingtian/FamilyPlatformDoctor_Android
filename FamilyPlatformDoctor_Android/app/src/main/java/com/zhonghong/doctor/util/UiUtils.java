package com.zhonghong.doctor.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zhonghong.doctor.R;

import java.io.File;

/**
 * Created by guobin.zheng on 2015/6/16.
 * <p/>
 * UI工具
 */
public class UiUtils {
    /**
     * Convert Dp to Pixel
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 重设 view 的宽高
     */
    public static void setViewLayoutParams(View view, int nWidth, int nHeight) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.height != nHeight || lp.width != nWidth) {
            lp.width = nWidth;
            lp.height = nHeight;
            view.setLayoutParams(lp);
        }
    }


    /**
     * 加载图片，设置宽高，解决加载大图片OOM问题
     *
     * @param sdv         VIEW
     * @param strImageUrl 图片加载地址
     * @param nWidth      宽
     * @param nHeight     高
     */
    public static void setImageToView(SimpleDraweeView sdv, String strImageUrl, int nWidth, int nHeight) {
        Uri uri = null;
        if (!TextUtils.isEmpty(strImageUrl))
            uri = Uri.parse(strImageUrl);
        setImageUriToView(sdv, uri, nWidth, nHeight);
    }

    /**
     * 加载图片，设置宽高，解决加载大图片OOM问题
     *
     * @param sdv     VIEW
     * @param nWidth  宽
     * @param nHeight 高
     */
    public static void setFileImageToView(SimpleDraweeView sdv, File file, int nWidth, int nHeight) {
        Uri uri = null;
        if (file != null && file.exists())
            uri = Uri.fromFile(file);
        setImageUriToView(sdv, uri, nWidth, nHeight);
    }

    private static void setImageUriToView(SimpleDraweeView sdv, Uri uri, int nWidth, int nHeight) {
        setViewLayoutParams(sdv, nWidth, nHeight);
        if (uri == null) {
            sdv.setImageURI(null);
            return;
        }
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)
                .setResizeOptions(new ResizeOptions(nWidth, nHeight))//调整大小
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(sdv.getController())
                .setImageRequest(request)
                .build();
        sdv.setController(controller);
    }

    public static void setSurfaceToView(Context mContext, SimpleDraweeView sdv, String strImageUrl, int nWidth, int nHeight) {
        if (TextUtils.isEmpty(strImageUrl))
            strImageUrl = UriUtil.getResPath(mContext, R.mipmap.icon_default_surface_no_pic);
        setImageToView(sdv, strImageUrl, nWidth, nHeight);
    }

    public static void setSurfaceToView(Context mContext, SimpleDraweeView sdv, String strImageUrl) {
        if (TextUtils.isEmpty(strImageUrl))
            strImageUrl = UriUtil.getResPath(mContext, R.mipmap.icon_default_surface_no_pic);
        setImageToView(sdv, strImageUrl);
    }

    /**
     * 加载图片，设置宽高，解决加载大图片OOM问题
     *
     * @param resources   资源
     * @param sdv         VIEW
     * @param strImageUrl 图片加载地址
     * @param nDpWidth    宽 必须是DP!!!!
     * @param nDpHeight   高 必须是DP!!!!
     */
    public static void setImageToViewByDp(Resources resources, SimpleDraweeView sdv, String strImageUrl, int nDpWidth, int nDpHeight) {
        int nWidth = dpToPx(nDpWidth, resources);
        int nHeight = dpToPx(nDpHeight, resources);
        setImageToView(sdv, strImageUrl, nWidth, nHeight);
    }

    /**
     * 加载图片
     *
     * @param sdv         VIEW
     * @param strImageUrl 图片加载地址
     */
    public static void setImageToView(SimpleDraweeView sdv, String strImageUrl) {
        sdv.setImageURI(TextUtils.isEmpty(strImageUrl) ? null : Uri.parse(strImageUrl));
    }

    public static Drawable getDrawable(Context mContext, int nDrawableID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mContext.getResources().getDrawable(nDrawableID, mContext.getTheme());
        } else {
            return mContext.getResources().getDrawable(nDrawableID);
        }
    }

}
