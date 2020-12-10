package io.antmedia.android.liveVideoBroadcaster;

public class User
{
    // Initialize variables
    public String username;
    String password;
    public String salt;
    public String passwordHash;

    public User(String username, String salt, String passwordHash)
    {
        setUsername(username);
        setSalt(salt);
        setPasswordHash(passwordHash);
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }
}
