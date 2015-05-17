package com.bao.wec.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.bao.wec.R;
import com.bao.wec.app.Constant;
import com.bao.wec.ui.base.BasePageActivity;
import com.bao.wec.ui.customview.dialog.NoticeDialog;
import com.bao.wec.utils.ImageUtils;
import com.bao.wec.utils.LogUtils;

import java.io.File;

public class RoomCreateActivity extends BasePageActivity {
    AQuery aq;

    Bitmap newAvatar;
    Uri imageCaptureUri;

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_create);
        aq = new AQuery(this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setupViews(Bundle bundle) {
        aq.id(R.id.title_bar_name).text("创建频道");
        aq.id(R.id.btn_back).visible().clicked(this,"aq_back");

    }

    @Override
    protected void setListener() {
        aq.id(R.id.create_add_image_btn).clicked(this,"aq_add_image");
        aq.id(R.id.create_bottom_btn).clicked(this,"aq_create");

    }

    @Override
    protected void fetchData() {

    }


    public void aq_back(){
        finish();
    }
    public void aq_add_image(){
        showAddImageWindow();
    }
    public void aq_create(){
        NoticeDialog.newInstanceShow(this,"创建成功").setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                redirectToActivity(mContext,RoomActivity.class);
                finish();
            }
        });

    }


    /**
     * 显示选择图片对话框
     */
    protected void showAddImageWindow() {
        View addImageView = getLayoutInflater().inflate(
                R.layout.dialog_add_photos, null);
        final PopupWindow popupWindow = new PopupWindow(addImageView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.AnimationPopup);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(aq.getView(), Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM, 0, 0);
        popupWindow.update();

        // 设置取消点击事件
        TextView addPhotosView_cancel = (TextView) addImageView
                .findViewById(R.id.cancel);
        addPhotosView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // 设置相机点击事件
        TextView addPhotosView_camera = (TextView) addImageView
                .findViewById(R.id.take_photos);
        addPhotosView_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                takePhoto();
            }
        });

        // 设置相册点击事件
        TextView addPhotosView_photos = (TextView) addImageView
                .findViewById(R.id.pick_photos);
        addPhotosView_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                pickPhoto();
            }
        });
    }

    /**
     * 调用系统直接拍照
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "time_"
                        + String.valueOf(System.currentTimeMillis())
                        + ".jpg");
        imageCaptureUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                imageCaptureUri);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constant.CODE.PICK_FROM_CAMERA);

    }


    /**
     * 选择图片
     */
    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, Constant.CODE.PICK_FROM_FILE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode){
            case Constant.CODE.PICK_FROM_FILE:
                startPhotoZoom(data.getData());
                break;
            case Constant.CODE.PICK_FROM_CAMERA:
                startPhotoZoom(imageCaptureUri);
                break;
            case Constant.CODE.ACTION_CROP:
                if(data!=null)
                    setPicToView(data);
                break;
        }
    }


    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", Constant.Count.UPLOAD_AVATAR_SIZE);
        intent.putExtra("outputY", Constant.Count.UPLOAD_AVATAR_SIZE);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constant.CODE.ACTION_CROP);
    }

    /**
     * 保存裁剪之后的图片数据到头像
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            newAvatar = extras.getParcelable("data");
            aq.id(R.id.create_add_image_btn).image(newAvatar);
            uploadAvatar();
        }
    }

    private void uploadAvatar() {
        //上传头像
        if (newAvatar != null) {
            String fileName = System.currentTimeMillis() + "tempAvatar.jpg";
            ImageUtils.saveBitmap(newAvatar, fileName);
            String path = Constant.Path.COMPLETE_PATH + fileName;
            File file = new File(path);
            LogUtils.i("保存裁剪头像后--->path = " + path);
            LogUtils.i("保存裁剪头像后--->file.exists() = " + file.exists());
            ShowToast("upload!");
//            final BmobFile avatar = new BmobFile(file);
//            avatar.upload(this, new UploadFileListener() {
//                @Override
//                public void onSuccess() {
//                    user.setAvatar(avatar.getFileUrl(MeActivity.this));
//                    user.update(MeActivity.this,new UpdateListener() {
//                        @Override
//                        public void onSuccess() {
//                            ShowToast("上传成功");
//                            LogUtils.i("上传成功" + avatar.getFileUrl(MeActivity.this));
//                        }
//
//                        @Override
//                        public void onFailure(int i, String s) {
//                            ShowToast("上传失败");
//                        }
//                    });
//                }
//                @Override
//                public void onFailure(int i, String s) {
//                    ShowToast("上传失败");
//                }
//            });
        }
    }


}
