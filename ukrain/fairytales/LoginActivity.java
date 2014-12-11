package com.fromwolfgar.ukrain.fairytales;

import com.fromwolfgar.entity.User;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements OnClickListener {

	EditText loginName;
	EditText loginPass;
	EditText loginPassConfirm;
	EditText loginEmail;
	Button btnOk;
	User user;

	SharedPreferences sPref;

	final String SAVED_NAME = "saved_name";
	final String SAVED_PASS = "saved_pass";
	final String SAVED_EMAIL = "saved_email";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		btnOk = (Button) findViewById(R.id.btnOk);

		loginName = (EditText) findViewById(R.id.loginName);
		loginPass = (EditText) findViewById(R.id.loginPass);
		loginPassConfirm = (EditText) findViewById(R.id.loginPassConfirm);
		loginEmail = (EditText) findViewById(R.id.loginEmail);

		
	}

	void saveText() {
		User user = new User();
		user.setName(loginName.getText().toString());
		user.setPass(loginPass.getText().toString());
		user.setEmail(loginEmail.getText().toString());
		this.user = user;
		
		sPref = getPreferences(MODE_PRIVATE);
		Editor ed = sPref.edit();
		ed.putString(SAVED_NAME, user.getName());
		ed.putString(SAVED_PASS, loginPass.getText().toString());
		ed.putString(SAVED_EMAIL, loginEmail.getText().toString());
		ed.commit();
		Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOk:
			
			if (loginPass.getText().toString() != loginPassConfirm.getText().toString()) {
				Toast.makeText(this, "Пароль не совпадает", Toast.LENGTH_SHORT)
						.show();
			} else {
				String email = loginEmail.getText().toString();
				if (email.contains("@")) {
					
					saveText();

				} else {
					Toast.makeText(this, "Вы ввели неправельно e-mail",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		default:
			break;
		}

	}

}
