package com.example.dllo.baidumusic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dllo.baidumusic.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by WangShuai on 16/10/21.
 */
public class StartActivity extends BaseActivity implements View.OnClickListener{
    private Button jump;
    private ImageView imageView;
    private Intent intent;
    private String str;
    private boolean click = true;

    @Override
    protected void initData() {

        intent = new Intent(StartActivity.this, MainActivity.class);
        str = "http://p1.img.cctvpic.com/nettv/newgame/cdn_pic/1777/mza_4077912966076464716.png";
        ImageAsync async = new ImageAsync();
        async.execute(str);
        timer.start();
    }

    @Override
    protected void initViews() {
        imageView = bindView(R.id.image_start);
        jump = bindView(R.id.time_start);
        setClick(this, jump);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void onClick(View view) {

        startActivity(intent);
        timer.cancel();
    }


    private CountDownTimer timer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long l) {
             String time = l/1000 + "";
        }

        @Override
        public void onFinish() {

                startActivity(intent);

        }
    };

    private class ImageAsync extends AsyncTask<String, Integer, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
