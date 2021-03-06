package io.antmedia.android.liveVideoBroadcaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static io.antmedia.android.liveVideoBroadcaster.LoginScreen.credentials;


public class SignUpScreen extends AppCompatActivity
{

    /*
     * Class Objects
     */
    Button signUp;
    TextView loginNow;
    EditText first;
    String firstName;
    EditText last;
    String lastName;
    EditText emailId;
    String userEmail;
    EditText pass1;
    String pass;
    EditText passVerif;
    String passVerify;

    private static final String SALTBANK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$~%^&*?.,";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        // Set OnClickListener for sign up button
        signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Initialize EditText objects
                first = (EditText) findViewById(R.id.first);
                firstName = first.getText().toString();

                last = (EditText) findViewById(R.id.last);
                lastName = last.getText().toString();

                emailId = (EditText) findViewById(R.id.emailreg);
                userEmail = emailId.getText().toString();

                pass1 = (EditText) findViewById(R.id.pass1);
                pass = pass1.getText().toString();

                passVerif = (EditText) findViewById(R.id.pass2);
                passVerify = passVerif.getText().toString();

                /*
                 Store email, and password after verifying info first
                 */
                if(firstName.isEmpty() || lastName.isEmpty() || userEmail.isEmpty() || pass.isEmpty() || passVerify.isEmpty())
                {
                    Toast.makeText(SignUpScreen.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                }
                else if(!pass.equals(passVerify))
                {
                    Toast.makeText(SignUpScreen.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                }
                else{
                    // Generate salt and hash
                    String pwSalt = generateSalt();
                    String pwHash = generateHash(pass, pwSalt);

                    // Create a new User
                    io.antmedia.android.liveVideoBroadcaster.User user = new io.antmedia.android.liveVideoBroadcaster.User(userEmail, pwSalt, pwHash);

                    // Add user to array list
                    credentials.add(user);

                    Toast.makeText(SignUpScreen.this, "Account Created.", Toast.LENGTH_LONG).show();

                    closeWindow(v);
                }
            }
        });

        // Set OnClickListener for login now text
        loginNow = (TextView) findViewById(R.id.dismiss);
        loginNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                closeWindow(v);
            }
        });
    }

    /**
     * Description: This method closes the pop up window
     *
     * @param v View to be closed
     */
    public void closeWindow(View v)
    {
        finish();
    }

    /**
     * Description: Generate salt to aid password security
     *
     * @return generated salt
     */
    public static String generateSalt()
    {
        final int SALTSIZE = 5;

        String passwordSalt;

        StringBuilder generatedSalt = new StringBuilder();

        for(int i = 0; i <  SALTSIZE; i++)
        {
            int character = (int)(Math.random()*SALTBANK.length());
            generatedSalt.append(SALTBANK.charAt(character));
        }

        passwordSalt = generatedSalt.toString();

        return passwordSalt;
    }

    /**
     * Description: Generates a hashcode of a concatenation of the users entered
     * password and the password salt
     *
     * @param password user entered password as a string
     * @param passwordSalt randomly generated salt as a string
     * @return hashed password
     */
    public static String generateHash(String password, String passwordSalt)
    {
        String pwConcat;
        char hashToChar;
        int ascii;

        pwConcat = password.concat(passwordSalt);

        // Create a new int array
        int[] asciiHash = new int[pwConcat.length()];

        // Converts string to char array
        char[] stringAsChars = pwConcat.toCharArray();

        // Converts hash to chars
        char[] hashAsChars = new char[pwConcat.length()];

        if(stringAsChars != null)
        {
            for(int charAt = 0; charAt < stringAsChars.length; charAt++)
            {
                ascii = stringAsChars[charAt];

                asciiHash[charAt] = ascii + 1;
            }
        }


        if(asciiHash != null)
        {

            for (int i = 0; i < asciiHash.length; i++)
            {
                hashToChar = (char) asciiHash[i];

                hashAsChars[i] = hashToChar;
            }
        }


        String passwordHash = new String(hashAsChars);

        return passwordHash;
    }
}
