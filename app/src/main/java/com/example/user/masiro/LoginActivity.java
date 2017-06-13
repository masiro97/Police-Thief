package com.example.user.masiro;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "OAuthSampleActivity";
    /**
     * client 정보를 넣어준다.
     */
    private static String OAUTH_CLIENT_ID = "uKeUnl_BDXO0Qx6FIpsd";
    private static String OAUTH_CLIENT_SECRET = "adVHUoX1Gd";
    private static String OAUTH_CLIENT_NAME = "Rodedown";

    final int _REQUEST_MSG_CODE = 10;

    private TextView mApiResultText;
    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;

    /** UI 요소들 */

    private OAuthLoginButton mOAuthLoginButton;
    EditText et;
    String about = ""; // 게임 설명
    public Boolean isLogin = false;

    //Personal Data

    String email = "";
    String nickname = "";
    String enc_id = "";
    String profile_image = "";
    String age = "";
    String gender = "";
    String id = "";
    String name = "";
    String birthday = "";
    String information = "";

    public void toastShow(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        OAuthLoginDefine.DEVELOPER_VERSION = true;
        mContext = this;

        initData();
        initView();

        try {
            InputStream is = getResources().openRawResource(R.raw.about);
            byte[] readStr = new byte[is.available()];
            is.read(readStr);
            is.close();
            about = new String(readStr);
        } catch (IOException e) {
            e.printStackTrace();
        }


        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Rodedown");
        dlg.setMessage(about);
        dlg.setNegativeButton("Close",null);
        dlg.setPositiveButton("OK",null);
        dlg.show();

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLogin){
                    Intent intent = new Intent(LoginActivity.this,NMapViewer.class);
                    startActivity(intent);
                    intent.putExtra("information",information);
                    startActivityForResult(intent,_REQUEST_MSG_CODE);
                    finish();
                }
                else toastShow("로그인을 해주세요");

            }
        });
    }

    private void initData() {
        mOAuthLoginInstance = OAuthLogin.getInstance();

        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);
		/*
		 * 2015년 8월 이전에 등록하고 앱 정보 갱신을 안한 경우 기존에 설정해준 callback intent url 을 넣어줘야 로그인하는데 문제가 안생긴다.
		 * 2015년 8월 이후에 등록했거나 그 뒤에 앱 정보 갱신을 하면서 package name 을 넣어준 경우 callback intent url 을 생략해도 된다.
		 */
        //mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME, OAUTH_callback_intent_url);
    }

    private void initView() {

        mApiResultText = (TextView) findViewById(R.id.api_result_text);
        et = (EditText)findViewById(R.id.editText);
        et.setText(id);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

    }

    @Override
    protected void onResume() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }
    /**
     * startOAuthLoginActivity() 호출시 인자로 넘기거나, OAuthLoginButton 에 등록해주면 인증이 종료되는 걸 알 수 있다.
     */
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {

                isLogin = true;
                et.setText(id);
                Toast.makeText(mContext,"Login에 성공했습니다",Toast.LENGTH_SHORT).show();
                new RequestApiTask().execute();

            } else {

                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();

            }
        };
    };

    public void onButtonClick(View v) throws Throwable {

        switch (v.getId()) {

            case R.id.buttonOAuthLogout: {
                mOAuthLoginInstance.logout(mContext);
                Toast.makeText(getApplicationContext(),"Logout에 성공했습니다",Toast.LENGTH_SHORT).show();
                isLogin = false;
                et.setText(null);
                break;
            }
            default:
                break;
        }
    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            mApiResultText.setText((String) "");
        }
        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/getUserProfile.xml";
            String at = mOAuthLoginInstance.getAccessToken(mContext);
            return mOAuthLoginInstance.requestApi(mContext, at, url);
        }
        protected void onPostExecute(String content) {
            Pasingversiondata(content);
            et.setText(id);
        }
    }

    private void Pasingversiondata(String data) { // xml 파싱

        String f_array[] = new String[9];

        try {

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            InputStream input = new ByteArrayInputStream(data.getBytes("UTF-8"));
            parser.setInput(input, "UTF-8");

            int parserEvent = parser.getEventType();

            String tag;

            boolean inText = false;

            boolean lastMatTag = false;

            int colIdx = 0;

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {

                    case XmlPullParser.START_TAG:

                        tag = parser.getName();

                        if (tag.compareTo("xml") == 0) {inText = false;}
                        else if (tag.compareTo("data") == 0) {inText = false;}
                        else if (tag.compareTo("result") == 0) {inText = false;}
                        else if (tag.compareTo("resultcode") == 0) {inText = false;}
                        else if (tag.compareTo("message") == 0) {inText = false;}
                        else if (tag.compareTo("response") == 0) {inText = false;}
                        else {inText = true;}

                        break;

                    case XmlPullParser.TEXT:

                        tag = parser.getName();

                        if (inText) {

                            if (parser.getText() == null) {f_array[colIdx] = "";}
                            else {f_array[colIdx] = parser.getText().trim();}
                            colIdx++;
                        }

                        inText = false;
                        break;

                    case XmlPullParser.END_TAG:

                        tag = parser.getName();
                        inText = false;
                        break;

                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.e("dd", "Error in network call", e);
        }

        id = f_array[0];
        nickname = f_array[1];
        profile_image = f_array[2];
        age = f_array[3];
        gender = f_array[4];
        enc_id = f_array[5];
        name = f_array[6];
        email = f_array[7];
        birthday = f_array[8];

        information = id +"," + enc_id + "," + name + "," + age + "," + email + "," + birthday;
    }
}
