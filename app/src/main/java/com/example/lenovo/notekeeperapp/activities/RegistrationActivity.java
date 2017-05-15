package com.example.lenovo.notekeeperapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.User;
import com.example.lenovo.notekeeperapp.utils.PreferencesHelper;
import com.example.lenovo.notekeeperapp.utils.user.FileUserStorage;
import com.example.lenovo.notekeeperapp.utils.user.UserStorage;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText fNameTv;
    private EditText lNameTv;
    private EditText emailTv;
    private EditText usernameTv;
    private EditText password1Tv;
    private EditText password2Tv;
    private RadioGroup genderRg;
    private EditText ageTv;

    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fNameTv = (EditText) findViewById(R.id.act_registr_edit_text_firstName);
        lNameTv = (EditText) findViewById(R.id.act_registr_edit_text_lastname);
        emailTv = (EditText) findViewById(R.id.act_registr_edit_text_email);
        usernameTv = (EditText) findViewById(R.id.act_registr_edit_text_username);
        password1Tv = (EditText) findViewById(R.id.act_registr_edit_text_password);
        password2Tv = (EditText) findViewById(R.id.act_registr_edit_text_confirm_password);
        genderRg = (RadioGroup) findViewById(R.id.act_reg_gender_rg);
        ageTv = (EditText) findViewById(R.id.act_registr_edit_text_age);

        findViewById(R.id.act_regist_btn_reg).setOnClickListener(this);

        userStorage = new FileUserStorage();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_regist_btn_reg:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        if(!checkAllFieldsValid()){
            return;
        }

        String fName = fNameTv.getText().toString();
        String lName = lNameTv.getText().toString();
        String email = emailTv.getText().toString();
        String username = usernameTv.getText().toString();
        String password = password1Tv.getText().toString();
        User.Gender gender = genderRg.getCheckedRadioButtonId() == R.id.act_reg_gender_rb_male ? User.Gender.MALE : User.Gender.FEMALE;
        int age = Integer.valueOf(ageTv.getText().toString());

        User user = new User(username, email, password, fName, lName, gender, age);

        userStorage.registerUser(user, new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });

    }

    private void handelUserFound(User user){
        if(user == null){
            // Very bad thing to hardcode strings in java file!!!!!!!
            Toast.makeText(this, "Something went wrong during registration", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesHelper preferancesHelper = PreferencesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private boolean checkAllFieldsValid(){
        // TODO check all fields
        return true;
    }
}
