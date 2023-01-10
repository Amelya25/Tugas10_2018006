package com.example.tugas6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import com.example.tugas6.databinding.ActivityMain3Binding;
import android.widget.Toast;
public class MainActivity3 extends AppCompatActivity implements
        View.OnClickListener {
    DbHelper DbHelper;
    private ActivityMain3Binding binding;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(MainActivity3.this,
                    ActivityProfile.class);
            startActivity(intent);
            finish();
        }
        session = new SessionManager(getApplicationContext());
        DbHelper = new DbHelper(this);
        SQLiteDatabase sqLiteDatabase =
                DbHelper.getWritableDatabase();
        binding.Signinbuttonid.setOnClickListener(this);
        binding.SignUpHerebuttonid.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String username =
                binding.signinusernameEditText.getText().toString();
        String password =
                binding.signinpasswordEditText.getText().toString();
        if (v.getId() == R.id.Signinbuttonid) {
            Boolean result = DbHelper.findPassword(username,
                    password);
            if (result == true) {
                Intent intent = new Intent(MainActivity3.this,
                        ActivityProfile.class);
                startActivity(intent);
                session.setLogin(true);
                finish();
            } else {
                Toast.makeText(this, "username and password didn`t match", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.SignUpHerebuttonid) {
            Intent intent = new Intent(MainActivity3.this,
                    RegisterActivity.class);
            startActivity(intent);
        }
    }
}
