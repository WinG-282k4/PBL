<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="index2.css" />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="index3.js"></script>
    <script src="index2.js"></script>
    <style>
      iframe {
        width: 100%;
        height: 100%;
        border: none;
      } 
      .menuhandle {
  background: #0e354d; /* Màu nền mặc định */
  border-left-color: transparent;
  color: #ffffff;
  position: relative;
  display: block;
  padding: 12px 14px 12px 44px;
  font-size: 14px;
  line-height: inherit;
  white-space: nowrap;
  border-left: 3px solid transparent;
  transition: color 0.3s, border-color 0.3s, background-color 0.3s;
}

/* Thêm hiệu ứng hover */
.menuhandle:hover {
  background: #092637; /* Màu nền khi hover */
  color: #ecf0f1; /* Màu chữ khi hover */
}
      
    </style>
  </head>
  <body>
    <aside class="sidebar" style="max-width: 175px">
      <div class="sidebar-header" style="position: relative">
        <a
          class="logo"
          href="components/dashboard/dashboad.html"
          target="main-content"
          >
        </a>
      
      </div>
      <div class="server-name" style="width: auto; max-width: 175px">
        myubuntu
      </div>
      <div class="sidebar-nav scrollable" tabindex="-1">
        <nav class="nav-main" role="navigation" aria-label="Main navigation">
          <ul class="menu-main" style="position: static">
            <li id="dashboard" class="is-selected">
              <a
   
                href="components/dashboard/dashboad.html"
                target="main-content"
                >Dashboards</a
              >
            </li>
            <li id="view" class="has-submenu is-expanded">
              <div class="menuhandle">Monitoring</div>


              <ul class="submenu" style="max-height: 153px">
                <li>
                  <a
                    href="check?action=problem"
                    target="main-content"
                    >Problems</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=listhost"
                    target="main-content"
                    >Hosts</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=latestdata"
                    target="main-content"
                    >Latest data</a
                  >
                </li>
              </ul>
            </li>
            <li id="config" class="has-submenu is-expanded">
             <div class="menuhandle">Data collection</div>


              <ul class="submenu" style="max-height: 214px">
                <li>
                  <a
                    href="check?action=listgrouphost"
                    target="main-content"
                    >Host groups</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=listhost"
                    target="main-content"
                    >Hosts</a
                  >
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
    </aside>
    <iframe src="components/dashboard/dashboad.html" name="main-content">
    </iframe>
  </body>
</html>
