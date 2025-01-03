<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="index2.css" />
  </head>
  <body>
    <div class="wrapper">
        <div class="server-name">myubuntu</div>
        <div class="signin-container">
          <div class="signin-logo"><div class="zabbix-logo"></div></div>
          <form action="login">
            <ul>
              <li>
                <label for="name">Username</label
                ><input
                  type="text"
                  id="name"
                  name="username"
                  value=""
                  maxlength="255"
                  autofocus="autofocus"
                />
              </li>
              <li>
                <label for="password">Password</label
                ><input
                  type="password"
                  id="password"
                  name="password"
                  value=""
                  maxlength="255"
                  autocomplete="off"
                />
              </li>
              <li>
                <input
                  type="checkbox"
                  id="autologin"
                  name="autologin"
                  value="1"
                  class="checkbox-radio"
                  checked="checked"
                />
              </li>
              <li>
                <button type="submit" id="enter" name="enter" value="Sign in">Sign in</button>

              </li>
            </ul>
          </form>
         
            
          </div>
        </div>
  </body>
</html>