package com.example.ex_200713_tabhost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    private EditText edtURL;
    private Button btnGo;
    private Button btnBack;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.one_fragment, container, false);

        findViewByIdFunction(viewGroup);

        // webView -> MyWebViewClient 등록
        webView.setWebViewClient(new MyWebViewClient());

        // webView 줌사용
        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://" + edtURL.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });

        return viewGroup;
    }

    private void findViewByIdFunction(ViewGroup viewGroup) {
        edtURL = (EditText) viewGroup.findViewById(R.id.edtURL);
        btnGo = (Button) viewGroup.findViewById(R.id.btnGo);
        btnBack = (Button) viewGroup.findViewById(R.id.btnBack);
        webView = (WebView) viewGroup.findViewById(R.id.webView);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
