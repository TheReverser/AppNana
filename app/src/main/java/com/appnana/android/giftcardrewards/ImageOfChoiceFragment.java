package com.appnana.android.giftcardrewards;

import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.appnana.android.giftcardrewards.adapter.ImageAdapter;
import com.appnana.android.giftcardrewards.listener.OnCloseListener;
import com.appnana.android.giftcardrewards.model.Settings;
import com.appnana.android.giftcardrewards.model.UserModel;
import com.appnana.android.giftcardrewards.view.ImageWithTextView;
import com.appnana.android.utils.Content;
import com.appnana.android.utils.MapizLog;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.hall.emojimap.EmojiMapUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageOfChoiceFragment extends TrackedSherlockDialogFragment implements OnClickListener {
    public static final String KEY_DATA = "ImageOfChoiceFragment:Data";
    private Gallery mGallery;
    private TextView mIgCaptionPreview;
    private OnCloseListener mOnCloseListener;
    private int mPosition;
    private ImageWithTextView mSelectedImageView;
    private OnItemSelectedListener onSelectedImage = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            ImageOfChoiceFragment.this.mPosition = position;
            ImageOfChoiceFragment.this.mSelectedImageView = (ImageWithTextView) view;
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            ImageOfChoiceFragment.this.mGallery.setSelection(0);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.Theme_MatchWidth_Dialog_FullWidth);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_of_choice, container, false);
        getDialog().getWindow().getAttributes().gravity = 7;
        findViews(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGallery();
        initIgCaptionPreview();
    }

    private void findViews(View rootView) {
        ((Button) rootView.findViewById(R.id.btn_share)).setOnClickListener(this);
        this.mGallery = (Gallery) rootView.findViewById(R.id.gallery);
        this.mIgCaptionPreview = (TextView) rootView.findViewById(R.id.txt_ig_caption_preview);
    }

    private void initGallery() {
        this.mGallery.setOnItemSelectedListener(this.onSelectedImage);
        this.mGallery.setAdapter(new ImageAdapter(getActivity(), Settings.getInstance().getIgImageCount(), UserModel.getInstance().getInvitationCode()));
    }

    private void initIgCaptionPreview() {
        this.mIgCaptionPreview.setText(EmojiMapUtil.replaceCheatSheetEmojis(getString(R.string.ig_share_caption)));
        this.mIgCaptionPreview.setMovementMethod(new ScrollingMovementMethod());
    }

    private Uri getImageViewUri(ImageView imageView) {
        File dir = getAlbumStorageDir();
        if (dir != null) {
            File imageFile = new File(dir, "instagram_share.jpg");
            try {
                FileOutputStream fos = new FileOutputStream(imageFile);
                imageView.buildDrawingCache();
                imageView.getDrawingCache().compress(CompressFormat.PNG, 100, fos);
                fos.close();
                return Uri.fromFile(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private File getAlbumStorageDir() {
        if (!isExternalStorageWritable()) {
            return null;
        }
        File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "AppNana");
        if (file.mkdirs()) {
            return file;
        }
        MapizLog.e("saveFile", "Directory not created");
        return file;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share /*2131558563*/:
                if (this.mSelectedImageView.success()) {
                    try {
                        Content.copy(getActivity(), EmojiMapUtil.replaceCheatSheetEmojis(getString(R.string.ig_share_caption)));
                        Toast.makeText(getActivity(), R.string.copy_success, 0).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), R.string.error_copy, 0).show();
                    }
                    this.mGATracker.send(new EventBuilder().setCategory("invite").setAction("share").setLabel("instagram-" + (this.mPosition + 1)).build());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(KEY_DATA, getImageViewUri(this.mSelectedImageView));
                    this.mOnCloseListener.onClose(bundle);
                    dismiss();
                    return;
                }
                AlertDialog.alert(getActivity(), (int) R.string.tips_important, (int) R.string.error_loading);
                return;
            default:
                return;
        }
    }
}
