package com.example.lenovo.notekeeperapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.pojos.User;
import com.example.lenovo.notekeeperapp.utils.PreferencesHelper;
import com.example.lenovo.notekeeperapp.utils.user.FileUserStorage;
import com.example.lenovo.notekeeperapp.utils.user.UserStorage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUserName;
    EditText editTextPassword;
    TextView textViewSignUp;
    Button buttonLogin;

    private UserStorage userStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName= (EditText) findViewById(R.id.act_login_edit_text_username);
        editTextPassword= (EditText) findViewById(R.id.act_login_edit_text_password);
        textViewSignUp= (TextView) findViewById(R.id.act_login_txtview_sign_up);
        buttonLogin= (Button) findViewById(R.id.act_login_btn_login);



        textViewSignUp.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        userStorage=new FileUserStorage();
    }

    private void handelUserFound(User user){
        if(user == null){
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesHelper preferancesHelper = PreferencesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean checkInputValid(){
        boolean valid = true;

        if(editTextUserName.getText().length() == 0 || editTextPassword.getText().length() == 0){
            Toast.makeText(this, "Username and password fields are required", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        return valid;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.act_login_btn_login:{

                if(!checkInputValid()){
                    return;
                }

                userStorage.checkAndGetUser(editTextUserName.getText().toString(), editTextPassword.getText().toString(), new UserStorage.UserFoundListener() {
                    @Override
                    public void onUserFound(User user) {
                        handelUserFound(user);
                    }
                });

//                if(editTextUserName.getText().length()==0 || editTextPassword.getText().length()==0){
//                    Toast.makeText(this,"Wrong Data",Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                }
                break;}
//                    user.setFirstName(editTextName.getText().toString());
//                    user.setLastName(editTextLastName.getText().toString());
//                    user.setUserName(editTextUserName.getText().toString());
//                    user.setGender(editTextGender.getText().toString());
//                    user.setAge(Integer.parseInt(editTextAge.getText().toString()));
//                    user.setBio(editTextBio.getText().toString());

//                    Intent intent = new Intent();
//                    intent.putExtra(EXTRA_MESSAGE_TO_EDIT, user);
//                    setResult(Activity.RESULT_OK, intent);
                    //  startActivityForResult(intent,1);
//                    finish();}

//                Intent intent = new Intent(ProfileActivity.this, EditActivity.class);
//
//                intent.putExtra(EXTRA_MESSAGE_TO_EDIT,user);
//                ProfileActivity.this.startActivity(intent);
            case R.id.act_login_txtview_sign_up:{
                startActivity(new Intent(this, RegistrationActivity.class));
            } }

    }
}
